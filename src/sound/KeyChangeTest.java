package sound;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class KeyChangeTest {
    /**
     * tests that getAllNotes if no notes exist
     */ 
    @Test
    public void getAllNotesTest(){
        KeyChange c = new KeyChange(new KeySig('A', 0,false));
        assertEquals(c.getAllNotes(), new ArrayList<Note>());
    }
}
