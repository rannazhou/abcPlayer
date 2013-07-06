package player;

import sound.Measure;

public class ABCTune{
    private final Header header;
    private final Music music;
       
    public ABCTune(Header header, Music music) {
        this.header = header;
        this.music = music;
        checkRep();
    }
    /*
     * Rep invariant: Header is not null
     * Music is not null
     * All measure lengths are equal to meter
     */
    private void checkRep(){
        assert header != null : "header is null";
        assert music != null : "music is null";
        for (String v : music.getVoices().keySet()){
            for (Measure m: music.getVoices().get(v).getMeasures()){
                Fraction mDuration = m.getTotalDuration();
                assert mDuration.equals(header.getMeter()) : "invalid measure length of " + mDuration + ", expected " + header.getMeter();
            }
        }
    }
    
    public Header getHeader() {
        return header;
    }
    public Music getMusic() {
        return music;
    }    
    
    public String toString(){
        return header + "\n" + music;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((header == null) ? 0 : header.hashCode());
        result = prime * result + ((music == null) ? 0 : music.hashCode());
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
        ABCTune other = (ABCTune) obj;
        if (header == null) {
            if (other.header != null){
                return false;
            }
        } else if (!header.equals(other.header)){
            return false;
        }
        if (music == null) {
            if (other.music != null){
                return false;
            }
        } else if (!music.equals(other.music)){
            return false;
        }
        return true;
    }
}
