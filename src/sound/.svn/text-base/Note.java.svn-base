package sound;

import java.util.ArrayList;
import java.util.List;

import player.Fraction;
import player.Visitor;

public class Note implements MusicElement{

    private Pitch pitch;
    private Fraction noteLength;
    //Whether or not a certain accidental was noted in the piece
    final private boolean accidentalNoted;
    
    public Note(Pitch pitch, Fraction noteLength, boolean accidentalNoted) {
        super();
        this.pitch = pitch;
        this.noteLength = noteLength;
        this.accidentalNoted = accidentalNoted;
        checkRep();
    }
    
    /*
     * Rep invariant:pitch is not null
     * Note length is not null
     */
    public void checkRep(){
        assert(this.pitch !=null);
        assert(this.noteLength !=null);
    }
    
    
    public Pitch getPitch() {
        return pitch;
    }
    
    public Fraction getNoteLength() {
        return noteLength;
    }
    
    
    public void setNoteLength(Fraction noteLength) {
        this.noteLength = noteLength;
    }
    
    
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    
    /**
     * @param takes in the key signature of the tune
     * @returns the adjusted pitch given the key signature
     */
    public void adjustKey(KeySig key) {
        this.pitch= KeySig.adjustPitch(this.pitch, key);
    }
    
    
    /**
     * @param takes in the accidentals of the measure of the tune
     * modifies the pitch given the previous accidentals
     * @returns whether or not it was changed
     */
    public boolean adjustAccidentals(List<Note> accidentals) {
        boolean changed = false;
        Pitch correct = this.pitch;
        char value = (char) this.pitch.getValue();
        for (Note n: accidentals){
            if (n.getPitch().getValue()==value){
                changed = true;
                correct = n.getPitch();
            }
        }
        this.pitch = correct;
        return changed;
    }
    
    public String toString(){
        return pitch.toString() + noteLength.toString();
    }
    /**
     * @return an array list with just this note
     */
    public List<Note> getAllNotes() {
        List<Note> note = new ArrayList<Note>();
        note.add(this);
        return note;
    }

    public void divideNoteDuration(int type) {
        if(type==2){
            this.noteLength= Fraction.multiply(noteLength, new Fraction(3, 2));
        }
        if(type==3){
            this.noteLength= Fraction.multiply(noteLength, new Fraction(2, 3));
        }
        if(type==4){
            this.noteLength= Fraction.multiply(noteLength, new Fraction(3, 4));
        }
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((noteLength == null) ? 0 : noteLength.hashCode());
        result = prime * result + ((pitch == null) ? 0 : pitch.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        Note other = (Note) obj;
        if (noteLength == null) {
            if (other.noteLength != null){
                return false;
            }
        } else if (!noteLength.equals(other.noteLength)){
            return false;
        }
        if (pitch == null) {
            if (other.pitch != null){
                return false;
            }
        } else if (!pitch.equals(other.pitch)){
            return false;
        }
        if(accidentalNoted!=other.accidentalNoted){
            return false;
        }
        return true;
    }

    public boolean isAccidentalNoted() {
        return accidentalNoted;
    }

    
    public Fraction getTotalDuration() {
        return this.noteLength;
    }
}
