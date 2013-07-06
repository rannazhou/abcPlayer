package sound;


import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import player.Fraction;
import player.Header;
import player.Visitor;
import static org.junit.Assert.*;

public class ElementVisitorTest {


    /**
     * tests that an element is not created if player is null
     */ 
    @Test(expected=AssertionError.class)
    public void nullPlayerElementVisitorTest(){
        Header header = new Header(1, "title", new KeySig('A', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
        new ElementVisitor(0, header, 12, null);
    }
    /**
     * tests that an element is not created if header is null
     */ 
    @Test(expected=AssertionError.class)
    public void nullHeaderElementVisitorTest(){
        SequencePlayer player;
        try {
            player = new SequencePlayer(120, 12);
            new ElementVisitor(0, null, 12, player);
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * tests single note can be created
     */ 
    @Test
    public void singleNoteElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('A', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            v.visit(n1);
            player2= new SequencePlayer(120, 12);
            player2.addNote(61, 0, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests single note can be created
     */ 
    @Test
    public void singleRestElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('A', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Rest n1 = new Rest(new Fraction(1,1));
            v.visit(n1);
            player2= new SequencePlayer(120, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests single chord is played correctly
     */ 
    @Test
    public void singleChordElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('C', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n2 = new Note(new Pitch('E'), new Fraction(1,1),false);
            List<MusicElement> notes = new ArrayList<MusicElement>();
            notes.add(n1);
            notes.add(n2);
            Chord c = new Chord(notes);
            v.visit(c);
            player2= new SequencePlayer(120, 12);
            player2.addNote(60, 0, 12);
            player2.addNote(64, 0, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests two chords in a row are played correctly (current time is used correctly)
     */ 
    @Test
    public void doubleChordElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('C', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n2 = new Note(new Pitch('E'), new Fraction(1,1),false);
            List<MusicElement> notes = new ArrayList<MusicElement>();
            notes.add(n1);
            notes.add(n2);
            Chord c = new Chord(notes);
            v.visit(c);
            v.visit(c);
            player2= new SequencePlayer(120, 12);
            player2.addNote(60, 0, 12);
            player2.addNote(64, 0, 12);
            player2.addNote(60, 12, 12);
            player2.addNote(64, 12, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests single triplet is played correctly
     */ 
    @Test
    public void singleTripletElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('C', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n2 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n3 = new Note(new Pitch('C'), new Fraction(1,1),false);
            List<MusicElement> notes = new ArrayList<MusicElement>();
            notes.add(n1);
            notes.add(n2);
            notes.add(n3);
            Tuplet c = new Tuplet(3,notes);
            v.visit(c);
            player2= new SequencePlayer(120, 12);
            player2.addNote(60, 0, 8);
            player2.addNote(64, 8, 8);
            player2.addNote(60, 16, 8);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests single measure is played correctly
     */ 
    @Test
    public void singleMeasureElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('C', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n2 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n3 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n4 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n5 = new Note(new Pitch('C'), new Fraction(1,1),false);
            List<MusicElement> notes = new ArrayList<MusicElement>();
            List<MusicElement> tripletNotes = new ArrayList<MusicElement>();
            tripletNotes.add(n1);
            tripletNotes.add(n2);
            tripletNotes.add(n3);
            Tuplet t = new Tuplet(3, tripletNotes);
            notes.add(t);
            notes.add(n4);
            notes.add(n5);
            Measure m = new Measure(notes);
            v.visit(m);
            player2= new SequencePlayer(120, 12);
            player2.addNote(60, 0, 8);
            player2.addNote(64, 8, 8);
            player2.addNote(60, 16, 8);
            player2.addNote(64, 24, 12);
            player2.addNote(60, 36, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests single measure with keychange is played correctly
     */ 
    @Test
    public void singleMeasureKeyChangeElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('C', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n2 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n3 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n4 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n5 = new Note(new Pitch('C'), new Fraction(1,1),false);
            List<MusicElement> notes = new ArrayList<MusicElement>();
            List<MusicElement> tripletNotes = new ArrayList<MusicElement>();
            tripletNotes.add(n1);
            tripletNotes.add(n2);
            tripletNotes.add(n3);
            Tuplet t = new Tuplet(3, tripletNotes);
            notes.add(t);
            KeyChange change = new KeyChange(new KeySig('B', -1, false));
            notes.add(change);
            notes.add(n4);
            notes.add(n5);
            Measure m = new Measure(notes);
            v.visit(m);
            player2= new SequencePlayer(120, 12);
            player2.addNote(60, 0, 8);
            player2.addNote(64, 8, 8);
            player2.addNote(60, 16, 8);
            player2.addNote(63, 24, 12);
            player2.addNote(60, 36, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests measure with accidental is played correctly
     */ 
    @Test
    public void accidentalMeasureElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('D', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n2 = new Note(new Pitch('E').accidentalTranspose(-1), new Fraction(1,1),true);
            Note n3 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n4 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n5 = new Note(new Pitch('C'), new Fraction(1,1),false);
            List<MusicElement> notes = new ArrayList<MusicElement>();
            List<MusicElement> tripletNotes = new ArrayList<MusicElement>();
            tripletNotes.add(n1);
            tripletNotes.add(n2);
            tripletNotes.add(n3);
            Tuplet t = new Tuplet(3, tripletNotes);
            notes.add(t);
            notes.add(n4);
            notes.add(n5);
            Measure m = new Measure(notes);
            v.visit(m);
            player2= new SequencePlayer(120, 12);
            player2.addNote(61, 0, 8);
            player2.addNote(63, 8, 8);
            player2.addNote(61, 16, 8);
            player2.addNote(63, 24, 12);
            player2.addNote(61, 36, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * tests single measure is played correctly
     */ 
    @Test
    public void singleVoiceElementVisitorTest(){
        SequencePlayer player;
        SequencePlayer player2;
        try {
            player = new SequencePlayer(120, 12);
            Header header = new Header(1, "title", new KeySig('C', 0, false), "composer", new Fraction(1, 4), new Fraction(4, 4), 120, new String[1]);
            Visitor v=new ElementVisitor(0, header, 12, player);
            Note n1 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n2 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n3 = new Note(new Pitch('C'), new Fraction(1,1),false);
            Note n4 = new Note(new Pitch('E'), new Fraction(1,1),false);
            Note n5 = new Note(new Pitch('C'), new Fraction(1,1),false);
            List<MusicElement> notes = new ArrayList<MusicElement>();
            List<MusicElement> tripletNotes = new ArrayList<MusicElement>();
            tripletNotes.add(n1);
            tripletNotes.add(n2);
            tripletNotes.add(n3);
            Tuplet t = new Tuplet(3, tripletNotes);
            notes.add(t);
            notes.add(n4);
            notes.add(n5);
            Measure m = new Measure(notes);
            List<Measure> measures = new ArrayList<Measure>();
            measures.add(m);
            measures.add(m);
            Voice voice = new Voice("", measures);
            v.visit(voice);
            player2= new SequencePlayer(120, 12);
            player2.addNote(60, 0, 8);
            player2.addNote(64, 8, 8);
            player2.addNote(60, 16, 8);
            player2.addNote(64, 24, 12);
            player2.addNote(60, 36, 12);
            player2.addNote(60, 48, 8);
            player2.addNote(64, 56, 8);
            player2.addNote(60, 64, 8);
            player2.addNote(64, 72, 12);
            player2.addNote(60, 84, 12);
            assertEquals(player.toString(),player2.toString());
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
