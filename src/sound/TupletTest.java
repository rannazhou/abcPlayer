package sound;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import player.Fraction;

public class TupletTest {
    /**
     * tests that a tuplet can be created
     */ 
    @Test
    public void basicTupletTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note(new Pitch('C'), new Fraction(1,1),false));
        elements.add(new Note(new Pitch('A'), new Fraction(1,1),false));
        new Tuplet(2,elements);
    }
    /**
     * tests that a measure is not created if elements is null
     */ 
    @Test(expected=AssertionError.class)
    public void nullTupletTest(){
        new Tuplet(3,null);
    }
    /**
     * tests that a measure is not created if elements is null
     */ 
    @Test(expected=AssertionError.class)
    public void wrongNumberTupletTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note(new Pitch('A'), new Fraction(1,1),false));
        new Tuplet(1,elements);
    }
}
