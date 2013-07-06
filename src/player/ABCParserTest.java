package player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import sound.Chord;
import sound.KeySig;
import sound.Measure;
import sound.MusicElement;
import sound.Note;
import sound.Pitch;
import sound.Voice;

public class ABCParserTest {
    
    @Test
    public void sample1(){
        List<MusicElement> m1 = new ArrayList<MusicElement>();
        
        // add all notes
        m1.add(new Note(new Pitch('C').octaveTranspose(-1), new Fraction(2, 1), true));
        m1.add(new Note(new Pitch('C'), new Fraction(2, 1), true));
        m1.add(new Note(new Pitch('C').octaveTranspose(2), new Fraction(1, 1), true));
        m1.add(new Note(new Pitch('C').octaveTranspose(3), new Fraction(1, 1), false));
        
        // add to list of measures
        List<Measure> measureList = new ArrayList<Measure>();
        measureList.add(new Measure(m1));
        
        // add to a voice
        Voice v = new Voice("", measureList);
        HashMap<String, Voice> voices = new HashMap<String, Voice>();
        voices.put("", v);
        
        // expected tune
        Header header = new Header(1, "sample 1", new KeySig('C', 0, false), null, null, null, 0, new String[]{""});
        Music music = new Music(voices);
        ABCTune expected = new ABCTune(header, music);
        
        // make actual tune
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new Token(TokenType.HEADER_FIELD, "X:1"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "T:sample 1"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "K:C"));
        tokens.add(new Token(TokenType.NOTE, "C,2"));
        tokens.add(new Token(TokenType.NOTE, "C2"));
        tokens.add(new Token(TokenType.NOTE, "c'"));
        tokens.add(new Token(TokenType.NOTE, "c''"));
        tokens.add(new Token(TokenType.BARLINE, "|]"));
        
        ABCTune actual = ABCParser.parse(tokens);
        assertEquals(header, actual.getHeader());
        assertEquals(music, actual.getMusic());
        assertEquals(expected, actual);    
    }
    
    @Test
    public void sample2(){
        List<MusicElement> m1 = new ArrayList<MusicElement>();
        
        // add all notes
        Note n1 = new Note(new Pitch('E'), new Fraction(1, 1), true);
        Note n2 = new Note(new Pitch('C'), new Fraction(1, 1), true);
        List<MusicElement> chord = new ArrayList<MusicElement>();
        chord.add(n1);
        chord.add(n2);
        m1.add(new Chord(chord));
        
        // add to list of measures
        List<Measure> measureList = new ArrayList<Measure>();
        measureList.add(new Measure(m1));
        
        // add to a voice
        Voice v = new Voice("", measureList);
        HashMap<String, Voice> voices = new HashMap<String, Voice>();
        voices.put("", v);
        
        // expected tune
        Header header = new Header(8, "Chord", new KeySig('C', 0, false), null, null, null, 0, new String[]{""});
        Music music = new Music(voices);
        ABCTune expected = new ABCTune(header, music);
        
        // make actual tune
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new Token(TokenType.HEADER_FIELD, "X:8\n"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "T:Chord\n"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "K:C\n"));
        tokens.add(new Token(TokenType.CHORD, "[EC]"));
        
        ABCTune actual = ABCParser.parse(tokens);
        

        //assertEquals(header, actual.getHeader());
        //assertEquals(music, actual.getMusic());
        assertEquals(expected.toString(), actual.toString());    
        }
    
    @Test
    public void sample3(){
        // add all notes
        List<MusicElement> m1 = new ArrayList<MusicElement>();
        m1.add(new Note(new Pitch('C'), new Fraction(1, 1), true));
        
        List<MusicElement> m2 = new ArrayList<MusicElement>();
        m2.add(new Note(new Pitch('E'), new Fraction(1, 1), true));
        
        List<MusicElement> m3 = new ArrayList<MusicElement>();
        m3.add(new Note(new Pitch('G'), new Fraction(1, 1), true));
        
        // add to list of measures
        List<Measure> measureList1 = new ArrayList<Measure>();
        measureList1.add(new Measure(m1));
        
        List<Measure> measureList2 = new ArrayList<Measure>();
        measureList2.add(new Measure(m2));
        
        List<Measure> measureList3 = new ArrayList<Measure>();
        measureList3.add(new Measure(m3));
        
        // add to a voice
        Voice v1 = new Voice("1", measureList1);
        Voice v2 = new Voice("2", measureList2);
        Voice v3 = new Voice("3", measureList3);
        
        HashMap<String, Voice> voices = new HashMap<String, Voice>();
        
        voices.put("1", v1);
        voices.put("2", v2);
        voices.put("3", v3);
        
        // expected tune
        Header header = new Header(1, "voices", new KeySig('C', 0, false), null, null, null, 0, new String[]{"1", "2", "3"});
        Music music = new Music(voices);
        ABCTune expected = new ABCTune(header, music);
        
        // make actual tune
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new Token(TokenType.HEADER_FIELD, "X:1\n"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "T:voices\n"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "K:C"));
        tokens.add(new Token(TokenType.VOICE, "V:1\n"));
        tokens.add(new Token(TokenType.VOICE, "V:2\n"));
        tokens.add(new Token(TokenType.VOICE, "V:3\n"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "Cm\n"));
        tokens.add(new Token(TokenType.VOICE, "V: 1\n"));
        tokens.add(new Token(TokenType.NOTE, "C"));
        tokens.add(new Token(TokenType.VOICE, "V: 2\n"));
        tokens.add(new Token(TokenType.NOTE, "E"));
        tokens.add(new Token(TokenType.VOICE, "V: 3\n"));
        tokens.add(new Token(TokenType.NOTE, "G"));
        
        ABCTune actual = ABCParser.parse(tokens);
        assertEquals(header.getComposer(), actual.getHeader().getComposer());
        assertEquals(header.getDefaultLength(), actual.getHeader().getDefaultLength());
        assertEquals(header.getKey(), actual.getHeader().getKey());
        assertEquals(header.getMeter(), actual.getHeader().getMeter());
        assertEquals(header.getTempo(), actual.getHeader().getTempo());
        assertArrayEquals(header.getVoiceNames(), actual.getHeader().getVoiceNames());
        
        assertEquals(header, actual.getHeader());
        assertEquals(music, actual.getMusic());
        assertEquals(expected, actual);   
    }
 
    @Test
    public void accidentalsTest(){
        List<MusicElement> m1 = new ArrayList<MusicElement>();
        List<MusicElement> m2 = new ArrayList<MusicElement>();
        
        // add all notes
        m1.add(new Note(new Pitch('C').accidentalTranspose(-2), new Fraction(1, 1), true));
        m1.add(new Note(new Pitch('C').accidentalTranspose(-1), new Fraction(1, 1), true));
        m1.add(new Note(new Pitch('C'), new Fraction(1, 1), true));
        m1.add(new Note(new Pitch('C'), new Fraction(1, 1), false));
        
        m2.add(new Note(new Pitch('C').accidentalTranspose(2), new Fraction(1, 1), true));
        m2.add(new Note(new Pitch('C').accidentalTranspose(1), new Fraction(1, 1), true));
        m2.add(new Note(new Pitch('C'), new Fraction(1, 1), true));
        m2.add(new Note(new Pitch('C'), new Fraction(1, 1), false));
        
        // add to list of measures
        List<Measure> measureList = new ArrayList<Measure>();
        measureList.add(new Measure(m1));
        measureList.add(new Measure(m2));
        
        // add to a voice
        Voice v = new Voice("", measureList);
        HashMap<String, Voice> voices = new HashMap<String, Voice>();
        voices.put("", v);
        
        // expected tune
        Header header = new Header(1, "accidentals test", new KeySig('C', 0, false), null, null, null, 0, new String[]{""});
        Music music = new Music(voices);
        ABCTune expected = new ABCTune(header, music);
        
        // make actual tune
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new Token(TokenType.HEADER_FIELD, "X:1"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "T:accidentals test"));
        tokens.add(new Token(TokenType.HEADER_FIELD, "K:C"));
        tokens.add(new Token(TokenType.NOTE, "__C"));
        tokens.add(new Token(TokenType.NOTE, "_C"));
        tokens.add(new Token(TokenType.NOTE, "=C"));
        tokens.add(new Token(TokenType.NOTE, "C"));
        tokens.add(new Token(TokenType.BARLINE, "|"));
        tokens.add(new Token(TokenType.NOTE, "^^C"));
        tokens.add(new Token(TokenType.NOTE, "^C"));
        tokens.add(new Token(TokenType.NOTE, "=C"));
        tokens.add(new Token(TokenType.NOTE, "C"));
        tokens.add(new Token(TokenType.BARLINE, "|"));
        
        ABCTune actual = ABCParser.parse(tokens);
        
        assertEquals(expected, actual);
    }
}
