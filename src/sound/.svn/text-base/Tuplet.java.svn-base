package sound;

import java.util.ArrayList;
import java.util.List;

import player.Fraction;
import player.Visitor;


public class Tuplet implements MusicElement{

    private final List<MusicElement> notes;
    private final int type;
    
    public Tuplet(int type, List<MusicElement> notes) {
        super();
        this.notes = notes;
        this.type = type;
        divideNoteDuration(type);
        checkRep();
    }
    
    /*
     * Rep invariant: 
     * type is either 2,3 or 4
     * Notes is not null
     * number of notes is equal to type
     */
    
    private void checkRep(){
        assert notes!=null : "tuplet list of notes be non-null";
        assert type == 2 || type == 3 || type == 4 : "type for tuplet must be 2, 3, or 4";
        assert type == notes.size() : "tuplet doesn't match given size";
    }

    /**
     * Changes the duration of each of the notes by dividing by the type
     */
    public void divideNoteDuration(int i) {
        for (MusicElement e: notes){
            e.divideNoteDuration(i);
        }
        
    }

    public List<MusicElement> getNotes() {
        return notes;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    
    public String toString(){
        String s = "(" + type;
        for(MusicElement m : notes){
            s += m;
        }
        return s;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<Note>();
        for (MusicElement e: this.notes){
            notes.addAll(e.getAllNotes());
        }
        return notes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + type;
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
        Tuplet other = (Tuplet) obj;
        for (int i = 0; i < notes.size(); i++){
            if (notes == null) {
                if (other.getNotes() != null){
                    return false;
                }
            } else if (!notes.get(i).equals(other.getNotes().get(i))){
                return false;
            }
        }
        if (type != other.type){
            return false;
        }
        return true;
    }
    
    public Fraction getTotalDuration() {
        Fraction duration = new Fraction(0,1);
        for (MusicElement e: this.notes){
            duration = duration.add(e.getTotalDuration());
        }
        return duration;
    }
}
