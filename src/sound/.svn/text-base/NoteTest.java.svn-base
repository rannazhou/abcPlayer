package sound;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import player.Fraction;

public class NoteTest {

    /**
     * tests that can create basic note and test equality
     */ 
    @Test
    public void basicNoteTest(){
        Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
        Note n2 = new Note(new Pitch('C'), new Fraction(1,1),true);
        Note n3 = new Note(new Pitch('B'), new Fraction(1,1),false);
        Note n4 = new Note(new Pitch('C'), new Fraction(2,1),false);
        assert(!n1.equals(n2));
        assert(!n1.equals(n3));
        assert(!n1.equals(n4));
    }
    /**
     * tests returns getAllNotes correctly
     */ 
    @Test
    public void basicGetAllNotesTest(){
        Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
        List<Note> list = new ArrayList<Note>();
        list.add(n1);
        assertEquals(n1.getAllNotes(),list);
    }
}
