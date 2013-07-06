package sound;

import java.util.ArrayList;
import java.util.List;

public class KeySig {
    private final char keyNote;
    //The accidental of the key signature (natural being 0)
    private final int keyAccidental;
    private final boolean modeMinor;
    
    /**
     * Constructor
     * @param keyNote base note
     * @param keyAccidental accidental key
     * @param modeMinor 
     */
    public KeySig(char keyNote, int keyAccidental, boolean modeMinor) {
        super();
        this.keyNote = keyNote;
        this.keyAccidental = keyAccidental;
        this.modeMinor = modeMinor;
        checkRep();
    }
    
    /**
     * Rep invariant: Base note is A-G, 
     * Accidental is not null, 
     * modeMinor is not null
     */
    private void checkRep(){
        assert (isValidNote(keyNote));
    }
    
    private boolean isValidNote(char keyNote2) {
        return (keyNote2>='A' && keyNote2<='G');
    }
    
    public char getKeyNote() {
        return keyNote;
    }
    
    public int getKeyAccidental() {
        return keyAccidental;
    }
    
    public boolean isModeMinor() {
        return modeMinor;
    }
    
    
    /**
     * 
     * @param p
     * @return if given pitch is consistent with this keySig
     */
    public boolean pitchInKeySig(Pitch p){
        int accidental = p.getAccidental();
        if (accidental == 1){
            if (getSharps(this).contains(new Character((char)p.getValue()))){
                return true;
            }else{
                return false;
            }
        }
        if (accidental == -1){
            if (getFlats(this).contains(new Character((char)p.getValue()))){
                return true;
            }else{
                return false;
            }
        }
        if (accidental == 0){
            return true;
        }
        return false;
    }
    
    
    /**
     * @param pitch intake pitch
     * @param key keysignature
     * modifies the pitch so its accidental is consistent with the 
     * key signature (leaving it unchanged if the original pitch has an accidental)
     */
    public static Pitch adjustPitch(Pitch pitch, KeySig key) {
        if (pitch.getAccidental()==0){
            char value = pitch.getBaseNote();
            if(key.isModeMinor()){
                key = getCorrespondingMajor(key);
            }
            // Add all sharps
            List<Character> sharps = getSharps(key);
            if (sharps.contains(new Character (value))){
                pitch=pitch.accidentalTranspose(1);
            }
            List<Character> flats = getFlats(key);
            if (flats.contains(new Character (value))){
                pitch =pitch.accidentalTranspose(-1);
            }
        }
        return pitch;
    }
    
    
    /**
     * @param key current key
     * @return sharps found in the given key signature
     */
    private static List<Character> getSharps(KeySig key) {
        char note = key.getKeyNote();
        int accidental = key.getKeyAccidental();
        List<Character> sharps = new ArrayList<Character>();
        //Natural keys
        if (accidental ==0){
            //A major
            if (note == 'A'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
            }
            //B major
            if (note == 'B'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
                sharps.add(new Character('D'));
                sharps.add(new Character('A'));
            }
            //C major
            if (note == 'C'){
            }
            //D major
            if (note == 'D'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
            }
            //E major
            if (note == 'E'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
                sharps.add(new Character('D'));
            }
            //G major
            if (note == 'G'){
                sharps.add(new Character('F'));
            }
            
        }
        
        //Sharp keys
        if (accidental ==1){
            //C# major
            if (note == 'C'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
                sharps.add(new Character('D'));
                sharps.add(new Character('A'));
                sharps.add(new Character('E'));
                sharps.add(new Character('B'));
            }
            
        }
        //Flat keys
        if (accidental ==-1){
            //Cb (B) major
            if (note == 'C'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
                sharps.add(new Character('D'));
                sharps.add(new Character('A'));
            }
            //Db (C#) major
            if (note == 'D'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
                sharps.add(new Character('D'));
                sharps.add(new Character('A'));
                sharps.add(new Character('E'));
                sharps.add(new Character('B'));
            }
            //Fb (E) major
            if (note == 'F'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
                sharps.add(new Character('D'));
            }
            //Gb (F#) major
            if (note == 'G'){
                sharps.add(new Character('F'));
                sharps.add(new Character('C'));
                sharps.add(new Character('G'));
                sharps.add(new Character('D'));
                sharps.add(new Character('A'));
                sharps.add(new Character('E'));
            }
            
        }
        return sharps;
    }
    
    /**
     * 
     * @param key the key
     * @return the flats associated with the given key signature
     */
    private static List<Character> getFlats(KeySig key) {
        char note = key.getKeyNote();
        int accidental = key.getKeyAccidental();
        List<Character> flats = new ArrayList<Character>();
        //Natural keys
        if (accidental ==0){
            //F major
            if (note == 'F'){
            }
        }
        
        //Sharp keys
        if (accidental ==1){
            //A# (Bb) major
            if (note == 'A'){
                flats.add(new Character('B'));
                flats.add(new Character('E'));
            }
            //D# (Eb) major
            if (note == 'D'){
                flats.add(new Character('B'));
                flats.add(new Character('E'));
                flats.add(new Character('A'));
            }
            //E#(F) major
            if (note == 'E'){
                flats.add(new Character('B'));
            }
            //G# (Ab) major
            if (note == 'G'){
                flats.add(new Character('B'));
                flats.add(new Character('E'));
                flats.add(new Character('A'));
                flats.add(new Character('D'));
            }
            
        }
        //Flat keys
        if (accidental ==-1){
            //Ab minor -> Cb major
            if (note == 'A'){
                flats.add(new Character('B'));
                flats.add(new Character('E'));
                flats.add(new Character('A'));
                flats.add(new Character('D'));
            }
            //Bb major
            if (note == 'B'){
                flats.add(new Character('B'));
                flats.add(new Character('E'));
            }
            //Eb major
            if (note == 'E'){
                flats.add(new Character('B'));
                flats.add(new Character('E'));
                flats.add(new Character('A'));
            }
        }
        
        return flats;
    }
    
    
    /**
     * 
     * @param key
     * @return corresponding major
     */
    private static KeySig getCorrespondingMajor(KeySig key) {
        char note = key.getKeyNote();
        int accidental = key.getKeyAccidental();
        //Natural keys
        if (accidental ==0){
            //A minor -> C major
            if (note == 'A'){
                return new KeySig('C', 0, false);
            }
            //B minor -> D major
            if (note == 'B'){
                return new KeySig('D', 0, false);
            }
            //C minor -> Eb major
            if (note == 'C'){
                return new KeySig('E', -1, false);
            }
            //D minor -> F major
            if (note == 'D'){
                return new KeySig('F', 0, false);
            }
            //E minor -> G major
            if (note == 'E'){
                return new KeySig('G', 0, false);
            }
            //F minor -> Ab major
            if (note == 'F'){
                return new KeySig('A', -1, false);
            }
            //G minor -> Bb major
            if (note == 'G'){
                return new KeySig('B', -1, false);
            }
            
        }
        
        //Sharp keys
        if (accidental ==1){
            //A# minor -> C# major
            if (note == 'A'){
                return new KeySig('C', 1, false);
            }
            //B# minor -> Eb major
            if (note == 'B'){
                return new KeySig('E', -1, false);
            }
            //C# minor -> E major
            if (note == 'C'){
                return new KeySig('E', 0, false);
            }
            //D# minor (Eb) -> Gb major
            if (note == 'D'){
                return new KeySig('G', -1, false);
            }
            //E# minor (F) -> Ab major
            if (note == 'E'){
                return new KeySig('A', -1, false);
            }
            //F# minor -> A major
            if (note == 'F'){
                return new KeySig('A', 0, false);
            }
            //G# minor -> B major
            if (note == 'G'){
                return new KeySig('B', 0, false);
            }
            
        }
        //Flat keys
        if (accidental ==-1){
            //Ab minor -> Cb major
            if (note == 'A'){
                return new KeySig('C', -1, false);
            }
            //Bb minor -> Db major
            if (note == 'B'){
                return new KeySig('D', -1, false);
            }
            //Cb minor (B) -> D major
            if (note == 'C'){
                return new KeySig('D', 0, false);
            }
            //Db minor (C#) -> E major
            if (note == 'D'){
                return new KeySig('E', 0, false);
            }
            //Eb minor  -> Gb major
            if (note == 'E'){
                return new KeySig('G', -1, false);
            }
            //Fb minor (E) -> G major
            if (note == 'F'){
                return new KeySig('G', 0, false);
            }
            //Gb minor (F#) -> A major
            if (note == 'G'){
                return new KeySig('A', 0, false);
            }
            
        }
        
        return key;
    }
    
    public String toString(){
        if (modeMinor)
            return keyNote + " " + keyAccidental + " " + "m";
        return keyNote + " " + keyAccidental;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + keyAccidental;
        result = prime * result + keyNote;
        result = prime * result + (modeMinor ? 1231 : 1237);
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        KeySig other = (KeySig) obj;
        if (keyAccidental != other.keyAccidental){
            return false;
        }
        if (keyNote != other.keyNote){
            return false;
        }
        if (modeMinor != other.modeMinor){
            return false;
        }
        return true;
    }
}
