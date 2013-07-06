package sound;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import player.Fraction;
import static org.junit.Assert.*;

public class ChordTest {

    /**
     * tests that a chord can be created
     */ 
    @Test
    public void basicChordTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note(new Pitch('C'), new Fraction(1,1),false));
        elements.add(new Note(new Pitch('A'), new Fraction(1,1),false));
        new Chord(elements);
    }
    /**
     * tests that a chord is not created if elements is null
     */ 
    @Test(expected=AssertionError.class)
    public void failbasicChordTest(){
        new Chord(null);
    }
    /**
     * tests that getAllNotes if no notes exist
     */ 
    @Test
    public void zeroGetAllNotesTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        Chord c = new Chord(elements);
        assertEquals(c.getAllNotes(), new ArrayList<Note>());
    }
    /**
     * tests that getAllNotes works
     */ 
    @Test
    public void basicGetAllNotesTest(){
        List<MusicElement> elements = new ArrayList<MusicElement>();
        Note c=new Note(new Pitch('C'), new Fraction(1,1),false);
        elements.add(c);
        Note b=new Note(new Pitch('B'), new Fraction(1,1),false);
        elements.add(b);
        Chord chord = new Chord(elements);
        List<Note> notes = new ArrayList<Note>();
        notes.add(c);
        notes.add(b);
        assertEquals(chord.getAllNotes(), notes);
    }
}
