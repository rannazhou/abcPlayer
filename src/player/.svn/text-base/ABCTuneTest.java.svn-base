package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import sound.KeySig;
import sound.Measure;
import sound.Voice;

public class ABCTuneTest {

    /**
     * tests that an ABC Tune can be created
     */ 
    @Test
    public void basicTuneTest(){
        Header h= new Header(0, "", new KeySig('A', 'C', true), "", new Fraction(1, 4), new Fraction(4, 4),40, new String[0]);
        HashMap<String,Voice> map = new HashMap<String, Voice>();
        List<Measure> measures = new ArrayList<Measure>();
        map.put("name",new Voice("name", measures));
        Music m = new Music(map);
        new ABCTune(h, m);
    }
    /**
     * tests that a header is not created if title is null
     */ 
    @Test(expected=AssertionError.class)
    public void noMusicTest(){
        Header h=new Header(0, null, new KeySig('A', 'C', true), "", new Fraction(1, 4), new Fraction(4, 4),40, new String[0]);
        new ABCTune(h, null);
    }
    /**
     * tests that a header is not created if title is null
     */ 
    @Test(expected=AssertionError.class)
    public void noHeaderTest(){
        new ABCTune(null, new Music(new HashMap<String, Voice>()));
    }
}
