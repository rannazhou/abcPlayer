package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import sound.Measure;
import sound.Voice;

public class MusicTest {

    /**
     * tests that a Music can be created
     */ 
    @Test
    public void basicMusicTest(){
        HashMap<String,Voice> map = new HashMap<String, Voice>();
        List<Measure> measures = new ArrayList<Measure>();
        map.put("name",new Voice("name", measures));
        new Music(map);
    }
    /**
     * tests that a Music can be created
     */ 
    @Test(expected=AssertionError.class)
    public void noMeasuresMusicTest(){
        HashMap<String,Voice> map = new HashMap<String, Voice>();
        new Music(map);
    }
    /**
     * tests that a header is not created if title is null
     */ 
    @Test(expected=AssertionError.class)
    public void nullMusicTest(){
        new Music(null);
    }
}
