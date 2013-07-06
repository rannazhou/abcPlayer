package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import sound.Measure;
import sound.Note;
import sound.Voice;

public class Music {
    private final HashMap<String, Voice> voices;
    
    // rep invariant:
    // each voice's measure is the same length
    // size of keyset is greater than 0
    //voices is not null
    public void checkRep(){
        assert (voices !=null);
        assert voices.keySet().size() > 0;
        int measureLength = -1;
        for (String v: voices.keySet()){
            if (measureLength == -1){ 
                measureLength = voices.get(v).getMeasures().size();
            }
            assert measureLength == voices.get(v).getMeasures().size() : measureLength + "!=" + voices.get(v).getMeasures().size();
        }
    }
    
    public HashMap<String, Voice> getVoices() {
        return voices;
    }
    
    public Music(HashMap<String, Voice> voices) {
        super();
        this.voices = voices;
        checkRep();
    }
    /**
     * 
     * @return list of all the musical elements contained in all the voices
     */
    public List<Note>getAllNotes(){
        Set<String> keys = voices.keySet();
        List<Note> notes = new ArrayList<Note>();
        for (String s: keys){
            Voice v = voices.get(s);
            for (Measure m: v.getMeasures()){
                notes.addAll(m.getAllNotes());
            }
        }
        return notes;
    }
    
    public String toString(){
        String s = "";
        for (String v : voices.keySet()){
            s = s.concat(voices.get(v).toString() + "\n");
        }
        return s;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((voices == null) ? 0 : voices.hashCode());
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
        Music other = (Music) obj;
        if (voices == null) {
            if (other.voices != null){
                return false;
            }
        } else if (!voices.equals(other.voices)){
            return false;
        }
        return true;
    }
}
