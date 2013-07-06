package sound;

import java.util.List;

import player.Fraction;
import player.Visitor;


public interface MusicElement {

    public void accept(Visitor v);
    public String toString();
    public List<Note> getAllNotes();
    //For use with tuplets
    public void divideNoteDuration(int type);
    public Fraction getTotalDuration();
}
