package sound;

import java.util.ArrayList;
import java.util.List;

import player.Fraction;
import player.Visitor;

public class Voice implements MusicElement{

    String name;
    private final List<Measure> measures;
    public Voice(String name, List<Measure> measures) {
        super();
        this.name = name;
        this.measures = measures;
        checkRep();
    }
    
    /*
     * Rep invariant: measures is not null
     * name is not null
     */
    public void checkRep(){
        assert(this.measures!=null);
        assert(this.name!=null);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Measure> getMeasures() {
        return measures;
    }
    //TODO what else?
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    public String toString(){
        String s = "voice:" + name + "\n";
        for (Measure m : measures){
            s += m;
        }
        return s;
    }
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<Note>();
        for (MusicElement e: measures){
            notes.addAll(e.getAllNotes());
        }
        return notes;
    }
    
    public void divideNoteDuration(int type) {
        for (MusicElement e: measures){
            e.divideNoteDuration(type);
        }
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((measures == null) ? 0 : measures.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Voice other = (Voice) obj;
        for (int i = 0; i < measures.size(); i++){
            if (measures.get(i) == null) {
                if (other.getMeasures().get(i) != null){
                    return false;
                }
            } else if (!measures.get(i).equals(other.getMeasures().get(i))){
                return false;
            }
        }
        if (name == null) {
            if (other.name != null){
                return false;
            }
        } else if (!name.equals(other.name)){
            return false;
        }
        return true;
    }
    
    public Fraction getTotalDuration() {
        Fraction duration = new Fraction(0,1);
        for (MusicElement e: this.measures){
            duration = duration.add(e.getTotalDuration());
        }
        return duration;
    }
}
