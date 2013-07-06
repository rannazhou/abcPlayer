package player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.ElementVisitor;
import sound.Note;
import sound.SequencePlayer;
import sound.Voice;

public class ABCPlayer {

    /**
     * Plays the input file using Java MIDI API and displays
     * header information to the standard output stream.
     * 
     * <p>Your code <b>should not</b> exit the application abnormally using
     * System.exit()</p>
     * 
     * @param file the name of input abc file
     * @return 
     * @throws IOException, UnrecoverableException 
     */
    public static SequencePlayer play(String file) throws IOException {
        ArrayList<Token> lexed = ABCLexer.lex(file); // ranna's part
        ABCTune parsed = ABCParser.parse (lexed); // sarah's part
        
        return play(parsed);
    }
    
    /**
     * @param an ABCTune tune
     * @returns SequencePlayer of the ABCTune
     */
    public static SequencePlayer play(ABCTune tune) {
        int beatsPerMinute = tune.getHeader().getTempo();
        int ticksPerQuarterNote = getTicks(tune)*4*tune.getHeader().getDefaultLength().getDenominator();
        try {
            SequencePlayer player = new SequencePlayer(beatsPerMinute, ticksPerQuarterNote);
            ElementVisitor visitor = new ElementVisitor(0, tune.getHeader(), ticksPerQuarterNote, player);
            for (String name: tune.getMusic().getVoices().keySet()){
                Voice v = tune.getMusic().getVoices().get(name);
                visitor.visit(v);
            }
            player.play();
            return player;
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidMidiDataException e) {
            return null;
        }
       
    }

    private static int getTicks(ABCTune tune) {
        List<Note> notes = tune.getMusic().getAllNotes();
        List<Fraction> durations = new ArrayList<Fraction>();
        for (Note n: notes){
            durations.add(n.getNoteLength());
        }
        return Fraction.leastCommonDenominator(durations);
    }
}
