package player;

import java.util.Arrays;

import sound.KeySig;

public class Header {
    private final int number;
    private final String title;
    private final KeySig key;
    private final String composer;
    private final Fraction defaultLength;
    private final Fraction meter;
    private final int tempo;
    private final String[] voiceNames;
    
    // TODO - add checkrep
    
    
    public Header(int number, String title, KeySig key, String composer,
            Fraction defaultLength, Fraction meter, int tempo,
            String[] voiceNames) {
        super();
        this.number = number;
        this.title = title;
        this.key = key;
        this.composer = composer;
        this.defaultLength = (defaultLength == null)? new Fraction(1, 8) : defaultLength;
        this.meter = (meter == null)? new Fraction(4, 4) : meter;
        this.tempo = (tempo == 0)? 100: tempo;
        this.voiceNames = voiceNames;
        checkRep();
    }
    /*
     * Rep invariant:
     * number, title and key are all not null
     */
    private void checkRep(){
        assert(this.title!=null);
        assert(this.key!=null);
    }
    public int getNumber() {
        return number;
    }
    
    public String getTitle() {
        return title;
    }
    
    public KeySig getKey() {
        return key;
    }
    
    public String getComposer() {
        return composer;
    }
    
    public Fraction getDefaultLength() {
        return defaultLength;
    }
    
    public Fraction getMeter() {
        return meter;
    }
    
    public int getTempo() {
        return tempo;
    }
    
    public String[] getVoiceNames() {
        return voiceNames;
    }
    
    public String toString(){
        String number = "X: " + getNumber() + "\n";
        String title = "T: " + getTitle() + "\n";
        String composer = "C: " + getComposer() + "\n";
        String defaultLength = "L: " + getDefaultLength() + "\n";
        String meter = "M: " + getMeter() + "\n";
        String tempo  = "Q: " + getTempo() + "\n";
        String key = "K: " + getKey() + "\n";
        String voices = "V: " + Arrays.toString(getVoiceNames()) + "\n";
        return number + title + composer + defaultLength + meter + tempo + key + voices;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((composer == null) ? 0 : composer.hashCode());
        result = prime * result
                + ((defaultLength == null) ? 0 : defaultLength.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((meter == null) ? 0 : meter.hashCode());
        result = prime * result + number;
        result = prime * result + tempo;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + Arrays.hashCode(voiceNames);
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
        Header other = (Header) obj;
        if (composer == null) {
            if (other.composer != null){
                return false;
            }
        } else if (!composer.equals(other.composer)){
            return false;
        }
        if (defaultLength == null) {
            if (other.defaultLength != null){
                return false;
            }
        } else if (!defaultLength.equals(other.defaultLength)){
            return false;
        }
        if (key == null) {
            if (other.key != null){
                return false;
            }
        } else if (!key.equals(other.key)){
            return false;
        }
        if (meter == null) {
            if (other.meter != null){
                return false;
            }
        } else if (!meter.equals(other.meter)){
            return false;
        }
        if (number != other.number){
            return false;
        }
        if (tempo != other.tempo){
            return false;
        }
        if (title == null) {
            if (other.title != null){
                return false;
            }
        } else if (!title.equals(other.title)){
            return false;
        }
        if (!Arrays.equals(voiceNames, other.voiceNames)){
            return false;
        }
        return true;
    }
}
