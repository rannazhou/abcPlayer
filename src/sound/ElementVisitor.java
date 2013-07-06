package sound;

import java.util.ArrayList;
import java.util.List;

import player.Fraction;
import player.Header;
import player.Visitor;


public class ElementVisitor implements Visitor {

    private int currentCount;
    private KeySig key;
    private Header header;
    private int ticks;
    private SequencePlayer player;
    private List<Note> accidentals;
    
    public ElementVisitor(int currentCount, Header header, int ticks, SequencePlayer player) {
        super();
        this.currentCount = currentCount;
        this.header = header;
        if(header!=null){
            this.key = header.getKey();
        }
        this.ticks = ticks;
        this.header = header;
        this.player = player;
        this.accidentals = new ArrayList<Note>();
        checkRep();
    }
    
    /*
     * Rep invariant: header is not null
     * player is not null
     * accidentals is not null
     */
    private void checkRep(){
        assert(header !=null);
        assert(player !=null);
        assert(accidentals !=null);
    }

    public KeySig getKey() {
        return key;
    }

    public Header getHeader() {
        return header;
    }
    
    public void visit(Chord chord) {
        int duration = 0;
        int minDuration =0;
        for (MusicElement note: chord.getAllNotes()){
            duration = Fraction.multiply(((Note)note).getNoteLength(), new Fraction(ticks,1)).toInt();
            note.accept(this);
            currentCount-=duration;
            if (duration<minDuration||minDuration==0){
                minDuration=duration;
            }
        }
        currentCount+=minDuration;
    }

    public void visit(Note note) {
        int duration = Fraction.multiply(note.getNoteLength(), new Fraction(ticks,1)).toInt();
        if(note.isAccidentalNoted()){
            this.accidentals.add(note);
        }else{
        if (!note.adjustAccidentals(accidentals)){
            note.adjustKey(key);
        }
        }
        player.addNote(note.getPitch().toMidiNote(), currentCount, duration);
        currentCount += duration;
    }

    public void visit(Rest rest) {
        int duration = Fraction.multiply(rest.getNoteLength(), new Fraction(ticks,1)).toInt();
        currentCount+= duration;
    }

    public void visit(Tuplet tuplet) {
        for (MusicElement note: tuplet.getNotes()){
//            int duration = Fraction.multiply(note.getNoteLength(), new Fraction(1,ticks)).toInt();
            note.accept(this);
        }
        
    }

    public void visit(Voice voice) {
        currentCount=0;
        for (Measure measure: voice.getMeasures()){
            measure.accept(this);
            }
    }

    
    public void visit(KeyChange keyChange) {
        key = keyChange.getKey();
        
    }

    
    public void visit(Measure measure) {
       this.accidentals.clear();
       for (MusicElement e: measure.getElements()){
           e.accept(this);
       }
        
    }
    
}
