package sound;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

public class KeySigTest {

    /**
     * tests can make basic keysig
     */ 
    @Test
    public void basicKeySigTest(){
        new KeySig('A', 0, true);
    }
    /**
     * tests can make basic keysig
     */ 
    @Test(expected=AssertionError.class)
    public void invalidKeySigTest(){
        new KeySig('H', 0, true);
    }
    /**
     * tests adjusts natural pitch
     */ 
    @Test
    public void naturalAdjustPitchTest(){
        KeySig k=new KeySig('C', 0, false);
        Pitch p = new Pitch('C');
        KeySig.adjustPitch(p, k);
        assertEquals(p, new Pitch('C'));
    }
    /**
     * tests adjusts natural pitch which should be flat
     */ 
    @Test
    public void flatAdjustPitchTest(){
        KeySig k=new KeySig('F', 0, false);
        Pitch p = KeySig.adjustPitch(new Pitch('B'), k);
        assertEquals(p, new Pitch('B').accidentalTranspose(-1));
    }
    /**
     * tests adjusts natural pitch which should be flat
     */ 
    @Test
    public void sharpAdjustPitchTest(){
        KeySig k=new KeySig('B', 0, false);
        Pitch p = KeySig.adjustPitch(new Pitch('C'), k);
        assertEquals(p, new Pitch('C').accidentalTranspose(1));
    }
    /**
     * tests adjusts natural pitch which should be flat
     */ 
    @Test
    public void charEqualTest(){
        Character c = new Character('C');
        Character d = new Character('C');
        ArrayList<Character> chars= new ArrayList<Character>();
        chars.add(c);
        assert(chars.contains(d));
    }
}
