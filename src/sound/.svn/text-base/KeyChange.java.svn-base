package sound;

import java.util.ArrayList;
import java.util.List;

import player.Fraction;
import player.Visitor;

public class KeyChange implements MusicElement{
    private final KeySig key;
    
    // Rep invariant: key is not null
    private void checkRep(){
        assert (key !=null);
    }
    /**
     * 
     * @param key the new key
     */
    public KeyChange(KeySig key) {
        super();
        this.key = key;
        checkRep();
    }

    /**
     * 
     * @return new key
     */
    public KeySig getKey() {
        return key;
    }

    /**
     * @param v visitor
     */
    public void accept(Visitor v) {
        v.visit(this);
        
    }
    public List<Note> getAllNotes() {
        return new ArrayList<Note>();
    }
    
    public void divideNoteDuration(int type) {
        
        
    }
    
    public Fraction getTotalDuration() {
        return new Fraction(0, 1);
    }
    
}