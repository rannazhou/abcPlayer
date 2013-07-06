package sound;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import player.Fraction;

public class VoiceTest {

    /**
     * tests that a voice can be created
     */ 
    @Test
    public void basicVoiceTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note(new Pitch('C'), new Fraction(1,1),false));
        elements.add(new Note(new Pitch('A'), new Fraction(1,1),false));
        List<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(elements));
        new Voice("name", measures);
    }
    /**
     * tests that a voice is not created if elements is null
     */ 
    @Test(expected=AssertionError.class)
    public void nullMeasuresVoiceTest(){
        new Voice("",null);
    }
    /**
     * tests that a voice is not created if elements is null
     */ 
    @Test(expected=AssertionError.class)
    public void nullNameVoiceTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note(new Pitch('C'), new Fraction(1,1),false));
        elements.add(new Note(new Pitch('A'), new Fraction(1,1),false));
        List<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(elements));
        new Voice(null,measures);
    }
}
