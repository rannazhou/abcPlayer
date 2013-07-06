package sound;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import player.Fraction;

public class MeasureTest {

    /**
     * tests that a measure can be created
     */ 
    @Test
    public void basicMeasureTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note(new Pitch('C'), new Fraction(1,1),false));
        elements.add(new Note(new Pitch('A'), new Fraction(1,1),false));
        new Measure(elements);
    }
    /**
     * tests that a measure is not created if elements is null
     */ 
    @Test(expected=AssertionError.class)
    public void failbasicMeasureTest(){
        new Measure(null);
    }
}
