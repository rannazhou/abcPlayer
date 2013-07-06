package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sound.Chord;
import sound.KeySig;
import sound.Measure;
import sound.MusicElement;
import sound.Note;
import sound.Pitch;
import sound.Rest;
import sound.Tuplet;
import sound.Voice;

public class ABCParser {
    private static final Pattern NOTE_TOKEN_REGEX = Pattern.compile (ABCLexer.NOTE);
    private static final Pattern REST_TOKEN_REGEX = Pattern.compile (ABCLexer.REST); 
    private static final Pattern CHORD_TOKEN_REGEX = Pattern.compile (ABCLexer.NOTE + "|" + ABCLexer.REST);
    private static final Pattern TUPLET_TOKEN_REGEX = Pattern.compile ("\\((\\d)" + "|" + ABCLexer.CHORD + "|" + ABCLexer.NOTE + "|" + ABCLexer.REST);
    private static final Pattern HEADER_TOKEN_REGEX 
    = Pattern.compile (
            ABCLexer.FIELD_NUMBER // FIELD_NUMBER
            + "|"
            + ABCLexer.FIELD_TITLE //FIELD_TITLE
            + "|"
            + ABCLexer.FIELD_COMPOSER //FIELD_COMPOSER
            + "|"
            + ABCLexer.FIELD_DEFAULT_LENGTH //FIELD_LENGTH
            + "|"
            + ABCLexer.FIELD_METER //FIELD_METER
            + "|"
            +  ABCLexer.FIELD_TEMPO//FIELD_TEMPO
            + "|"
            + ABCLexer.FIELD_KEY //FIELD_KEY
      );
    private static final Pattern VOICE_TOKEN_REGEX = Pattern.compile(ABCLexer.FIELD_VOICE);
    private static final Pattern BARLINE_TOKEN_REGEX = Pattern.compile(ABCLexer.BARLINE);
    private static final Pattern Nth_REPEAT_TOKEN_REGEX = Pattern.compile(ABCLexer.NthREPEAT);
    
    // attributes for header
    private static int field_number;
    private static String field_title;
    private static KeySig field_key;
    private static String field_composer;
    private static Fraction field_defaultLength;
    private static Fraction field_meter;
    private static int field_tempo;
    private static String[] field_voiceNames;
    
    // attributes for error checking
    private static boolean fieldKeyFound = false;
    /**
     * @param tokens List of tokens to be parsed
     * @return resulting ABCTune from tokens 
     */
    public static ABCTune parse (ArrayList<Token> tokens) {
        Iterator<Token> itr = tokens.iterator();
        
        // keep track of notes and measures
        Set<String> voices = new HashSet<String>();
        HashMap<String, Voice> music = new HashMap<String, Voice>();
        HashMap<String, List<Measure>> voiceMeasures = new HashMap<String, List<Measure>>();
        HashMap<String, List<MusicElement>> voiceNotes = new HashMap<String, List<MusicElement>>();
        
        // repeat - starts from beginning by default
        HashMap<String, List<Measure>> voiceMeasuresRepeat = new HashMap<String, List<Measure>>();
        HashMap<String, List<MusicElement>> voiceNotesRepeat = new HashMap<String, List<MusicElement>>();
        HashMap<String, Boolean> voiceRepeat = new HashMap<String, Boolean>();
        
        // keep track of current voice
        String currVoice = "";
        
        // add current voice (for pieces without voices)
        voices.add("");
        voiceMeasures.put("", new ArrayList<Measure>());
        voiceNotes.put("", new ArrayList<MusicElement>());
        
        voiceMeasuresRepeat.put("", new ArrayList<Measure>());
        voiceNotesRepeat.put("", new ArrayList<MusicElement>());
        voiceRepeat.put("", true);
        
        while (itr.hasNext()){
            Token t = itr.next();
            TokenType type = t.getType();
            String val = t.getValue();
            
            if (type == TokenType.HEADER_FIELD){
                //System.out.println(TokenType.HEADER_FIELD + ": " + val);
                if (fieldKeyFound){
                    System.out.println("Warning: Header field after field key - " + val);
                }
                parseHeaderField(val);
            }
            else if (type == TokenType.VOICE){
                //System.out.println(TokenType.VOICE + ": " + val + " for voice " + currVoice);
                String voiceVal = parseVoice(val);
                if (!voices.contains(voiceVal)){
                    voices.add(voiceVal);
                    voiceMeasures.put(voiceVal, new ArrayList<Measure>());
                    voiceNotes.put(voiceVal, new ArrayList<MusicElement>());
                    
                    voiceMeasuresRepeat.put(voiceVal, new ArrayList<Measure>());
                    voiceNotesRepeat.put(voiceVal, new ArrayList<MusicElement>());
                    voiceRepeat.put(voiceVal, true);
                }
                else{
                    currVoice = voiceVal;
                }
            }
            else if (type == TokenType.NOTE){
                Note note = lexNote(val);
                //System.out.println(TokenType.NOTE + ": " + note + " for voice " + currVoice);

                voiceNotes.get(currVoice).add(note);
                if (voiceRepeat.get(currVoice) == true){
                    voiceNotesRepeat.get(currVoice).add(note);
                }                
            }
            else if (type == TokenType.REST){
                Rest rest = parseRest(val);
                //System.out.println(TokenType.REST +": " + rest + " for voice " + currVoice);

                voiceNotes.get(currVoice).add(rest);
                if (voiceRepeat.get(currVoice) == true){
                    voiceNotesRepeat.get(currVoice).add(rest);
                }
            }
            else if (type == TokenType.CHORD){

                Chord chord = parseChord(val);
                //System.out.println(TokenType.CHORD +": " + chord + " for voice " + currVoice);

                voiceNotes.get(currVoice).add(chord);
                if (voiceRepeat.get(currVoice) == true){
                    voiceNotesRepeat.get(currVoice).add(chord);
                }
            }
            else if (type == TokenType.TUPLET){
                //System.out.println(TokenType.TUPLET +": " + val + " for voice " + currVoice);

                Tuplet tuplet = parseTuplet(val);
                voiceNotes.get(currVoice).add(tuplet);
                if (voiceRepeat.get(currVoice) == true){
                    voiceNotesRepeat.get(currVoice).add(tuplet);
                }
            }
            else if (type == TokenType.Nth_REPEAT){
                Matcher m = Nth_REPEAT_TOKEN_REGEX.matcher(val);            
                while(m.find()){
                    if (m.group(2) != null){
                        if (m.group(2).equals("1")){
                            // 1st ENDING - stop repeating
                            voiceRepeat.put(currVoice, false);
                        }
                        if(m.group(2).equals("2")){
                            // do nothing    
                        }
                    }
                }
                //System.out.println(TokenType.Nth_REPEAT +": " + val + " for voice " + currVoice);
            }
            else if (type == TokenType.BARLINE){
                //System.out.println(TokenType.BARLINE +": " + val + " for voice " + currVoice);
                // add previous measure to voiceMeasures, restart voiceNotes
                voiceMeasures.get(currVoice).add(new Measure(voiceNotes.get(currVoice)));
                voiceNotes.put(currVoice, new ArrayList<MusicElement>());
                
                // do same thing - add to repeat
                voiceMeasuresRepeat.get(currVoice).add(new Measure(voiceNotesRepeat.get(currVoice)));
                voiceNotesRepeat.put(currVoice, new ArrayList<MusicElement>());
                
                Matcher m = BARLINE_TOKEN_REGEX.matcher(val);    
                if(m.find()){
                    if (m.group(2) != null){
                        // DOUBLE BARLINE - start repeat from here
                        voiceRepeat.put(currVoice, true);

                        voiceNotesRepeat.put(currVoice, new ArrayList<MusicElement>());
                        voiceMeasuresRepeat.put(currVoice, new ArrayList<Measure>());
                    }
                    if (m.group(3) != null){
                        // MAJOR START - start repeat from here
                        voiceRepeat.put(currVoice, true);

                        voiceNotesRepeat.put(currVoice, new ArrayList<MusicElement>());
                        voiceMeasuresRepeat.put(currVoice, new ArrayList<Measure>());
                    }
                     if (m.group(4) != null){
                         // MAJOR END - start repeat from here
                         voiceRepeat.put(currVoice, true);

                         voiceNotesRepeat.put(currVoice, new ArrayList<MusicElement>());
                         voiceMeasuresRepeat.put(currVoice, new ArrayList<Measure>());
                    }
                     if (m.group(5) != null){
                         // REPEAT END - add music to be repeated
                         voiceMeasuresRepeat.get(currVoice).add(new Measure(voiceNotesRepeat.get(currVoice)));
                         voiceMeasures.get(currVoice).addAll(voiceMeasuresRepeat.get(currVoice));
                         
                         // empty out repeated music, set voiceRepeat to false
                         voiceNotesRepeat.put(currVoice, new ArrayList<MusicElement>());
                         voiceMeasuresRepeat.put(currVoice, new ArrayList<Measure>());
                         voiceRepeat.put(currVoice, false);
                    }
                     if (m.group(6) != null){
                         // REPEAT START - start repeat from here
                         voiceRepeat.put(currVoice, true);

                         voiceNotes.put(currVoice, new ArrayList<MusicElement>());
                         voiceMeasures.put(currVoice, new ArrayList<Measure>());
                    }
                     if (m.group(7) != null){
                         // MEASURE BARLINE - do nothing
                    }
                    
                }
            }
            
            else if (type == TokenType.COMMENT){
                //System.out.println(TokenType.COMMENT +": " + val);
                // do nothing
            }
            else if (type == TokenType.EOF){
                //System.out.println(TokenType.EOF +": " + val);
                // do nothing
            }
        }
        // if didn't end with barline
        for (String v: voices){
            if (voiceNotes.get(v).size() != 0){
            voiceMeasures.get(v).add(new Measure(voiceNotes.get(v)));
            }
        }
        
        // go through voices and add them to voiceNames
        if (voiceMeasures.get("").size() == 0){
            voices.remove("");
        }
        
        String[] voiceNames = new String[voices.size()];
        Iterator<String> voiceItr = voices.iterator();
        int i = 0;
        while(voiceItr.hasNext()){
            String v = voiceItr.next(); 
            voiceNames[i] = (String) v;
            i++;
        }

        field_voiceNames = voiceNames;
        
        Header myHeader = new Header(field_number, field_title, field_key, field_composer, field_defaultLength, field_meter, field_tempo, field_voiceNames);

        for (String v : voiceNames){
            Voice voice = new Voice(v, voiceMeasures.get(v));
            music.put(v, voice);
        }
        
        Music myMusic = new Music(music);
        ABCTune myTune = new ABCTune(myHeader, myMusic);

        return myTune;
    }

    /**
     * @param input String from token representing a Note
     * @return Note created from String
     */
    private static Note lexNote(String input) {
        
        Matcher m = NOTE_TOKEN_REGEX.matcher(input);        
        char baseNote = '0';
        String octave = "";
        String accidental = "";
        Fraction f = null;
        
        while(m.find()){
            if (m.group(2) != null){
                // ACCIDENTAL
                accidental = m.group(2);
            }
            if (m.group(3) != null){
                // BASENOTE
                baseNote = m.group(3).charAt(0);
            }
            if (m.group(4) != null){
                // OCTAVE
                Matcher octaveMatch = Pattern.compile("'|,").matcher(input);
                while(octaveMatch.find()){
                    octave = octave.concat(octaveMatch.group());
                }
            }
            if (m.group(5) != null){
                // DURATION
                f = parseDuration(m.group(5));    
            }
        }
        //System.out.println(accidental + " " + baseNote + " " + octave + " " + f.getNumerator() + "/" + f.getDenominator());
        return makeNote(accidental, baseNote, octave, f);
    }
    /**
     * Helper method to make Note by reading String representation
     * @param accidental
     * @param baseNote
     * @param octave
     * @param noteLength
     * @return Note created from String
     */
    private static Note makeNote (String accidental, char baseNote, String octave, Fraction noteLength){
        Pitch p;
        // change to lowercase baseNote
        if (Character.isLowerCase(baseNote)){
            p = new Pitch(Character.toUpperCase(baseNote));
            p = p.octaveTranspose(1);
        }
        else{
            p = new Pitch(baseNote);
        }
        
        // determine accidental
        int a = 0;
        boolean isAccidental = false;
        if (accidental.equals("__")){
            a = -2;
            isAccidental = true;
        }
        else if (accidental.equals("_")){
            a = -1;
            isAccidental = true;
        }
        else if (accidental.equals("=")){
            a = 0;
            isAccidental = true;
        }
        else if (accidental.equals("^")){
            a = 1;
            isAccidental = true;
        }
        else if (accidental.equals("^^")){
            a = 2;
            isAccidental = true;
        }
        
        //determine octave
        int o = 0;
        for (int i = 0; i < octave.length(); i++){
            if (octave.charAt(i) == ','){
                o -= 1;
            }
            if (octave.charAt(i) == '\''){
                o += 1;
            }
        }
        
        p = p.octaveTranspose(o);
        p = p.accidentalTranspose(a);
        
        if (noteLength == null){
            noteLength = new Fraction(1,1);
        }
        return new Note(p, noteLength, isAccidental);
    }
    /**
     * @param input String from token representing a Fraction
     * @return Fraction created from String
     */
    private static Fraction parseDuration(String input) {
        if (input == null || input.equals("")){
            return new Fraction(1,1);
        }
        
        final Pattern FRACTION_TOKEN_REGEX = Pattern.compile (ABCLexer.DURATION_FRACTION);
        
        boolean slash = false;
        int numerator = 1;
        int denominator = 2;
        
        Matcher m = FRACTION_TOKEN_REGEX.matcher(input);
        while(m.find()){
            if (m.group(3) != null && !m.group(3).equals("")){
                // NUMERATOR
                numerator = Integer.parseInt(m.group(3));
            }
            if (m.group(4) != null && !m.group(4).equals("")){
                // SLASH
                slash = true;
            }
             if (m.group(5) != null && !m.group(5).equals("")){
                // DENOMINATOR
                denominator = Integer.parseInt(m.group(5));
            }
             if (m.group(6) != null && !m.group(6).equals("")){
                // NUMERATOR - with no denominator or slash
                numerator = Integer.parseInt(m.group(6)); 
            }
        }
        
        if (!slash){
            //System.out.println(new Fraction(numerator, 1));
            return new Fraction(numerator, 1);
        }
        //System.out.println(new Fraction(numerator, denominator));
        return new Fraction(numerator, denominator);
    }

    /**
     * @param input String from token representing a Chord
     * @return Chord created from String
     */
    private static Chord parseChord(String input) {
        Matcher m = CHORD_TOKEN_REGEX.matcher(input);
        List<MusicElement> list = new ArrayList<MusicElement>();
        
        while(m.find()){
            if (m.group(1) != null){
                // NOTE
                list.add(lexNote(m.group(1)));
            }
            else if (m.group(11) != null){
                // REST
                list.add(parseRest(m.group(11)));
            }
        }
        return new Chord(list);
    }
    
    /**
     * @param input String from token representing a Tuplet
     * @return Tuplet created from String
     */
    private static Tuplet parseTuplet(String input) {
        Matcher m = TUPLET_TOKEN_REGEX.matcher(input);
        List<MusicElement> list = new ArrayList<MusicElement>();
        
        int num = 0;

        while(m.find()){
            if (m.group(1) != null && m.group(1) != ""){
                // TUPLET SPEC
                num = Integer.parseInt(m.group(1));
            }
            else if (m.group(2) != null){
                // CHORD
                list.add(parseChord(m.group(2)));
            }
            else if (m.group(21) != null){
                // NOTE
                list.add(lexNote(m.group(21)));
            }
            else if (m.group(31) != null){
                // REST
                list.add(parseRest(m.group(31)));
            }
        }
        
        return new Tuplet(num, list);
    }

    /**
     * Set values in header to match input
     * @param input String from token representing a Header Field and it's value
     * @return Note created from String
     */
    private static void parseHeaderField(String input) {
        Matcher m = HEADER_TOKEN_REGEX.matcher(input);
        
        while(m.find()){
            //System.out.println("HEADER FIELD:" + m.group());
            //for (int i = 1; i <= m.groupCount(); i++){
            //    System.out.println(i + ": " + m.group(i));
            //}
            if (m.group(1) != null){
                // FIELD_NUMBER
                field_number = parseFieldNumber(input);
            }
            if (m.group(3) != null){
                // FIELD_TITLE
                field_title = parseFieldTitle(input);
            }
            if (m.group(5) != null){
                // FIELD_COMPOSER
                field_composer = parseFieldComposer(input);
            }
            if (m.group(7) != null){
                // FIELD_LENGTH
                field_defaultLength = parseFieldLength(input);
            }
            if (m.group(11) != null){
                // FIELD_METER
                field_meter = parseFieldMeter(input);
            }
            if (m.group(18) != null){
                // FIELD_TEMPO
                field_tempo = parseFieldTempo(input);
            }
            if(m.group(20) != null){
                // FIELD_KEY
                fieldKeyFound = true;
                field_key = parseFieldKey(input);
            }
        }        
    }
    
    /**
     * 
     * @param input string
     * @returns an integer with the tempo of the default note
     */
    private static int parseFieldTempo(String input) {
        Matcher m = Pattern.compile(ABCLexer.FIELD_TEMPO).matcher(input);
        int tempo = 0;
        
        if (m.find()){
            tempo = Integer.parseInt(m.group(2));
        }
        return tempo;
    }
    
    /**
     * @param input string
     * @return 
     */
    private static Fraction parseFieldMeter(String input) {
        Matcher m = Pattern.compile(ABCLexer.FIELD_METER).matcher(input);

        int numerator = 0;
        int denominator = 0;
        
        if (m.find()){
            if(m.group(3) != null){
                // C| = cut time
                numerator = 2;
                denominator = 2;
            }
            else if (m.group(4) != null){
                // C = common time
                numerator = 4;
                denominator = 4;
            }
            if (m.group(5) != null){
                // must be a fraction
                numerator = Integer.parseInt(m.group(6));
                denominator = Integer.parseInt(m.group(7));
            }
        }
        //System.out.println(numerator + "/" + denominator);
        return new Fraction(numerator, denominator);
    }

    private static Fraction parseFieldLength(String input) {
        Matcher m = Pattern.compile(ABCLexer.FIELD_DEFAULT_LENGTH).matcher(input);

        int numerator = 0;
        int denominator = 0;
        
        if (m.find()){
            numerator = Integer.parseInt(m.group(3));
            denominator = Integer.parseInt(m.group(4));
        }
        
        return new Fraction(numerator, denominator);
    }

    private static String parseFieldComposer(String input) {
        Matcher m = Pattern.compile(ABCLexer.FIELD_COMPOSER).matcher(input);

        String composer = "";
        
        if (m.find()){
            composer = m.group(2);
        }
        
        return composer;
    }

    private static String parseFieldTitle(String input) {
        Matcher m = Pattern.compile(ABCLexer.FIELD_TITLE).matcher(input);

        String title = "";
        
        if (m.find()){
            title = m.group(2);
        }
        
        return title;
    }

    private static int parseFieldNumber(String input) {
        Matcher m = Pattern.compile(ABCLexer.FIELD_NUMBER).matcher(input);

        int num = 0;
        
        if (m.find()){
            num = Integer.parseInt(m.group(2));
        }
        
        return num;
    }
    
    private static KeySig parseFieldKey(String input) {
        Matcher m = Pattern.compile(ABCLexer.FIELD_KEY).matcher(input);

        char keyNote = '0';
        int keyAccidental = 0;
        boolean modeMinor = false;
        
        while(m.find()){
            if (m.group(2) != null){
                // KEY
                keyNote = m.group(2).charAt(0);
            }
            if (m.group(3) != null && m.group(3).length() > 0){
                // ACCIDENTAL
                char a = m.group(3).charAt(0);
                if (a == '#'){
                    // # = sharp
                    keyAccidental = 1;
                }
                if (a == 'b'){
                    // b = flat
                    keyAccidental = -1;
                }
            }
            if (m.group(4) != null && m.group(4).length() > 0){
                // MINOR
                if (m.group(4).charAt(0) == 'm')
                    modeMinor = true;
            }
        }
        
        return new KeySig(keyNote, keyAccidental, modeMinor);
    }
    
    /**
     * @param input String from token representing a Rest
     * @return rest created from String
     */
    private static Rest parseRest(String input) {
        Matcher m = REST_TOKEN_REGEX.matcher(input);
        Fraction f = null;
        if (m.find()){
            f = parseDuration(m.group(2)); 
        }
        //System.out.println("rest: " + f.getNumerator() + "/" + f.getDenominator());
        return new Rest(f);
    }  
    
    /**
     * @param input String from token representing a Voice name
     * @return string name taken from input
     */
    private static String parseVoice(String input) {
        Matcher m = VOICE_TOKEN_REGEX.matcher(input);
        String val = "";
        
        if (m.find()){
           val = m.group(2);
        }
        return val;
    }  
    
    public static void main(String[] args){
        //ABCParser.lexNote("A21/80");
        //ABCParser.parseDuration("21/8");
    }
}
