package player;

import org.junit.Test;

import sound.KeySig;

public class HeaderTest {

    /**
     * tests that a header can be created
     */ 
    @Test
    public void basicHeaderTest(){
        new Header(0, "", new KeySig('A', 'C', true), "", new Fraction(1, 4), new Fraction(4, 4),40, new String[0]);
    }
    
    /**
     * tests if title is null then error
     */ 
    @Test(expected=AssertionError.class)
    public void noTitleTest(){
        new Header(0, null, new KeySig('A', 'C', true), "", new Fraction(1, 4), new Fraction(4, 4),40, new String[0]);
    }
    
    
    /**
     * tests if keysig is null then error
     */ 
    @Test(expected=AssertionError.class)
    public void noKeySigTest(){
        new Header(0, "title", null, "", new Fraction(1, 4), new Fraction(4, 4),40, new String[0]);
    }
}
