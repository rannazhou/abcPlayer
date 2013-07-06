package player;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.Chord;
import sound.KeySig;
import sound.Measure;
import sound.MusicElement;
import sound.Note;
import sound.Pitch;
import sound.SequencePlayer;
import sound.Voice;

public class ABCPlayerTest {

//    @Test
    public void playSingleNoteTest(){
        Header header = new Header(0, "", new KeySig('A', 'C', true), "", new Fraction(1, 4), new Fraction(4, 4),40, new String[0]);
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note(new Pitch('C'), new Fraction(1,1),false));
        Measure m = new Measure(elements);
        List<Measure> measures = new ArrayList<Measure>();
        measures.add(m);
        Voice v = new Voice("1", measures);
        HashMap<String,Voice> voices = new HashMap<String, Voice>();
        voices.put("1",v);
        Music music = new Music(voices);
        ABCTune tune = new ABCTune(header, music);
        ABCPlayer.play(tune);
    }
    
    
//    @Test
    public void playSimpleChordTest(){
        Header header = new Header(0, "", new KeySig('A', 'C', true), "", new Fraction(1, 4), new Fraction(4, 4),40, new String[0]);
        List<MusicElement> elements = new ArrayList<MusicElement>();
        List<MusicElement> notes = new ArrayList<MusicElement>();
        notes.add(new Note(new Pitch('C'), new Fraction(1,1),false));
        notes.add(new Note(new Pitch('E'), new Fraction(1,1),false));
        elements.add(new Chord(notes));
        elements.add(new Note(new Pitch('G'), new Fraction(1,1),false));
        elements.add(new Chord(notes));
        Measure m = new Measure(elements);
        List<Measure> measures = new ArrayList<Measure>();
        measures.add(m);
        Voice v = new Voice("1", measures);
        HashMap<String,Voice> voices = new HashMap<String, Voice>();
        voices.put("1",v);
        Music music = new Music(voices);
        ABCTune tune = new ABCTune(header, music);
        ABCPlayer.play(tune);
    }
    
    /**
     * tests piece1
     */
//    @Test
    public void playPiece1Test(){
        try {
            SequencePlayer actual = ABCPlayer.play("sample_abc/piece1.abc");
            // create a new player, with 140 beats (i.e. quarter note) per
            // minute, with 12 tick per quarter note (so can handle both triplets and sixteenth notes
            int factor = 16;
            SequencePlayer player = new SequencePlayer(140, 12*factor);

            player.addNote(new Pitch('C').toMidiNote(), 0, 12*factor);
            player.addNote(new Pitch('C').toMidiNote(), 12*factor, 12*factor);
            player.addNote(new Pitch('C').toMidiNote(), 24*factor, 9*factor);
            player.addNote(new Pitch('D').toMidiNote(), 33*factor, 3*factor);
            player.addNote(new Pitch('E').toMidiNote(), 36*factor, 12*factor);
            //start second measure
            player.addNote(new Pitch('E').toMidiNote(), 48*factor, 9*factor);
            player.addNote(new Pitch('D').toMidiNote(), 57*factor, 3*factor);
            player.addNote(new Pitch('E').toMidiNote(), 60*factor, 9*factor);
            player.addNote(new Pitch('F').toMidiNote(), 69*factor, 3*factor);
            player.addNote(new Pitch('G').toMidiNote(), 72*factor, 24*factor);
            //start third measure
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96*factor, 4*factor);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100*factor, 4*factor);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104*factor, 4*factor);
            player.addNote(new Pitch('G').toMidiNote(), 108*factor, 4*factor);
            player.addNote(new Pitch('G').toMidiNote(), 112*factor, 4*factor);
            player.addNote(new Pitch('G').toMidiNote(), 116*factor, 4*factor);
            player.addNote(new Pitch('E').toMidiNote(), 120*factor, 4*factor);
            player.addNote(new Pitch('E').toMidiNote(), 124*factor, 4*factor);
            player.addNote(new Pitch('E').toMidiNote(), 128*factor, 4*factor);
            player.addNote(new Pitch('C').toMidiNote(), 132*factor, 4*factor);
            player.addNote(new Pitch('C').toMidiNote(), 136*factor, 4*factor);
            player.addNote(new Pitch('C').toMidiNote(), 140*factor, 4*factor);
           //start fourth measure
            player.addNote(new Pitch('G').toMidiNote(), 144*factor, 9*factor);
            player.addNote(new Pitch('F').toMidiNote(), 153*factor, 3*factor);
            player.addNote(new Pitch('E').toMidiNote(), 156*factor, 9*factor);
            player.addNote(new Pitch('D').toMidiNote(), 165*factor, 3*factor);
            player.addNote(new Pitch('C').toMidiNote(), 168*factor, 24*factor);

            assertEquals(player.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * tests piece2
     */
    @Test
    public void playPiece2Test(){
        try {
            SequencePlayer actual = ABCPlayer.play("sample_abc/piece2.abc");
            // create a new player, with 200 beats (i.e. quarter note) per
            // minute, with 12 tick per quarter note (so can handle both triplets and sixteenth notes
            int factor =8;
            SequencePlayer player = new SequencePlayer(200, 12*factor);
            
            //start first measure
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 0, 6*factor);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 0, 6*factor);
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 6*factor, 6*factor);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 6*factor, 6*factor);
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 18*factor, 6*factor);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 18*factor, 6*factor);
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 30*factor, 6*factor);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 30*factor, 6*factor);
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 36*factor, 12*factor);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 36*factor, 12*factor);
            
            //start second measure
            player.addNote(new Pitch('G').toMidiNote(), 48*factor, 12*factor);
            player.addNote(new Pitch('B').toMidiNote(), 48*factor, 12*factor);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 48*factor, 12*factor);
            player.addNote(new Pitch('G').toMidiNote(), 72*factor, 12*factor);
           
            //start third measure
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96*factor, 18*factor);
            player.addNote(new Pitch('G').toMidiNote(), 114*factor, 6*factor);
            player.addNote(new Pitch('E').toMidiNote(), 132*factor, 12*factor);
            
            //start fourth measure
            player.addNote(new Pitch('E').toMidiNote(), 144*factor, 6*factor);
            player.addNote(new Pitch('A').toMidiNote(), 150*factor, 12*factor);
            player.addNote(new Pitch('B').toMidiNote(), 162*factor, 12*factor);
            player.addNote(new Pitch('B').accidentalTranspose(-1).toMidiNote(), 174*factor, 6*factor);
            player.addNote(new Pitch('A').toMidiNote(), 180*factor, 12*factor);

            //start fifth measure
            player.addNote(new Pitch('G').toMidiNote(), 192*factor, 8*factor);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 200*factor, 8*factor);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 208*factor, 8*factor);
            player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(), 216*factor, 12*factor);
            player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), 228*factor, 6*factor);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 234*factor, 6*factor);
            
            //start sixth measure
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 246*factor, 12*factor);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 258*factor, 6*factor);
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 264*factor, 6*factor);
            player.addNote(new Pitch('B').toMidiNote(), 270*factor, 18*factor);

            assertEquals(player.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
