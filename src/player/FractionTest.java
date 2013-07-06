package player;

import org.junit.Test;


public class FractionTest {

    /**
     * tests the fraction constructor works
     */ 
    @Test
    public void basicFractionTest(){
        Fraction fraction = new Fraction(1, 4);
        assert fraction.getNumerator() == 1;
        assert fraction.getDenominator() == 4;
    }
    
    /**
     * tests that trying to create an invalid fractions result in an error
     */ 
    @Test(expected=AssertionError.class)
    public void divideByZeroTest(){
        Fraction fraction = new Fraction(1, 0);
    }
    
    @Test
    public void compareToEqualTest(){
        Fraction f1 = new Fraction (2, 4);
        Fraction f2 = new Fraction (1, 2);
        assert f1.compareTo(f2) == 0;
    }
    
    @Test
    public void compareToLessThanTest(){
        Fraction f1 = new Fraction (1, 3);
        Fraction f2 = new Fraction (1, 2);
        assert f1.compareTo(f2) == -1;
    }
    
    @Test
    public void compareToGreaterThanTest(){
        Fraction f1 = new Fraction (7, 9);
        Fraction f2 = new Fraction (11, 23);
        assert f1.compareTo(f2) == 1;
    }
    
    @Test
    public void addTest(){
        Fraction f1 = new Fraction (2, 8);
        Fraction f2 = new Fraction (1, 3);
        Fraction expected = new Fraction (7, 12);
        assert f1.add(f2).equals(expected);
    }
    
    @Test
    public void reduceTest(){
        Fraction f1 = new Fraction (2, 8);
        Fraction expected = new Fraction (1, 4);
        assert f1.reduce().equals(expected);
    }
}
