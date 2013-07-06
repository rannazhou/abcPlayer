package sound;

import java.util.ArrayList;
import java.util.List;

import player.Fraction;
import player.Visitor;

public class Measure implements MusicElement{

    private final List<MusicElement> elements;

    public Measure(List<MusicElement> elements) {
        super();
        this.elements = elements;
        checkRep();
    }
    
    // Rep invariant: elements list is not null
    private void checkRep(){
        assert (elements !=null);
    }

    public List<MusicElement> getElements() {
        return elements;
    }
    
    public void addElement(MusicElement element) {
        this.elements.add(element);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    public String toString(){
        String s = "";
        for (MusicElement m : elements){
            s += " " + m;
        }
        s += " |";
        return s;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<Note>();
        for (MusicElement e: elements){
            notes.addAll(e.getAllNotes());
        }
        return notes;
    }

    public void divideNoteDuration(int type) {
        for (MusicElement e: elements){
            e.divideNoteDuration(type);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((elements == null) ? 0 : elements.hashCode());
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
        Measure other = (Measure) obj;
        for (int i = 0; i < elements.size(); i++){
            if (elements.get(i) == null) {
                if (other.getElements().get(i) != null){
                    return false;
                }
            } else if (!elements.get(i).equals(other.getElements().get(i))){
                return false;
            }
        }
        return true;
    }

    
    public Fraction getTotalDuration() {
        Fraction duration = new Fraction(0,1);
        for (MusicElement e: this.elements){
            duration = duration.add(e.getTotalDuration());
        }
        return duration;
    }
}
