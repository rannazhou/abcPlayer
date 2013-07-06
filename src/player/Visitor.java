package player;

import sound.Chord;
import sound.KeyChange;
import sound.Measure;
import sound.Note;
import sound.Rest;
import sound.Tuplet;
import sound.Voice;

public interface Visitor {
    public void visit(Chord chord);

    public void visit(Note note);

    public void visit(Rest rest);

    public void visit(Tuplet tuplet);

    public void visit(Voice voice);

    public void visit(KeyChange keyChange);

    public void visit(Measure measure);
    
}
