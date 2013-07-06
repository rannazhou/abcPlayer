package sound;


import org.junit.Test;

import player.Fraction;

public class RestTest {

    /**
     * tests that a rest can be created
     */ 
    @Test
    public void basicRestTest(){
        new Rest(new Fraction(1, 2));
    }
    /**
     * tests that a measure is not created if elements is null
     */ 
    @Test(expected=AssertionError.class)
    public void nullTimeRestTest(){
        new Rest(null);
    }
}
