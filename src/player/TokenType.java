package player;


/*
 * Token type for the ABC language.
 */
public enum TokenType {
    // Lexer token types
    NOTE,
    REST,    
    CHORD,
    TUPLET,
    VOICE,
    BARLINE,
    Nth_REPEAT,
    HEADER_FIELD, // all fields except voice, 
    //ex: "field-title: field-value"
    
    //Tokens for which we will not do anything
    COMMENT,
    EOF, // end of file
    
    //TODO: need to add different kinds of barlines
    
}
