package sound;

import java.util.ArrayList;
import java.util.List;


import player.Fraction;
import player.Visitor;


public class Chord implements MusicElement{

    
    //The notes in the chord (should only be notes or rests)
    private final List<MusicElement> notes;

    /**
     * Returns the notes in the chord
     * @return notes
     */
    public List<MusicElement> getNotes() {
        return notes;
    }

    /**
     * Constructor
     * @param notes the notes
     */
    public Chord(List<MusicElement> notes) {
        super();
        this.notes = notes;
        checkRep();
    }
    
    /**
     * Rep invariant: notes is not null
     */
    private void checkRep(){
        try{
            assert(notes!=null);
        }catch (AssertionError e){
            throw new AssertionError ("Notes is null");
        }
    }
    
    /**
     * @param v the visitor
     */
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    /**
     * @return string of chord
     */
    public String toString(){
        String s = "[";
        for (MusicElement m : notes){
            s = s.concat(m.toString());
        }
        return s + "]";
    }
    
    /**
     * @returns a list of the notes that makes up the Chord
     */
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<Note>();
        for (MusicElement e: this.notes){
            notes.addAll(e.getAllNotes());
        }
        return notes;
    }
    
    /**
     * Divides the notes in chord by duration
     */
    public void divideNoteDuration(int type) {
        for (MusicElement e: notes){
            e.divideNoteDuration(type);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
        Chord other = (Chord) obj;
        for (int i = 0; i < notes.size(); i++){
            if (notes == null) {
                if (other.getNotes() != null){
                    return false;
                }
            } else if (!notes.get(i).equals(other.getNotes().get(i))){
                return false;
            }
        }
        return true;
    }

    public Fraction getTotalDuration() {
        Fraction duration = null;
        for (MusicElement e: this.notes){
            if (duration == null||duration.compareTo(e.getTotalDuration())<0){
                duration=e.getTotalDuration();
            }
        }
        return duration;
    }
    
    
}
