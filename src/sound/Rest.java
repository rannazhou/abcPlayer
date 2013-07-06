package sound;

import java.util.ArrayList;
import java.util.List;

import player.Fraction;
import player.Visitor;


public class Rest implements MusicElement{

    private Fraction noteLength;

    public Rest(Fraction noteLength) {
        super();
        this.noteLength = noteLength;
        checkRep();
    }
    
    /*
     * Rep invariant: noteLength is not null
     */
    public void checkRep(){
        assert(noteLength!=null);
    }

    public Fraction getNoteLength() {
        return noteLength;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    public String toString(){
        return "z" + noteLength.toString();
    }

    public List<Note> getAllNotes() {
        return new ArrayList<Note>();
    }

    public void divideNoteDuration(int type) {
        this.noteLength= Fraction.multiply(noteLength, new Fraction(1, type));
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((noteLength == null) ? 0 : noteLength.hashCode());
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
        Rest other = (Rest) obj;
        if (noteLength == null) {
            if (other.noteLength != null){
                return false;
            }
        } else if (!noteLength.equals(other.noteLength)){
            return false;
        }
        return true;
    }

    
    public Fraction getTotalDuration() {
        return this.noteLength;
    }
}
