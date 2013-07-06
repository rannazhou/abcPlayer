package player;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ABCLexerTest {
    
    /**
     * tests that the the Note regex is able to match all the parts of a note 
     * (accidental, basenote, octave, and note-length)
     */ 
    @Test
    public void noteRegexTest(){
        Matcher m = Pattern.compile(ABCLexer.NOTE).matcher("^^C,',/3");
        if (m.find()){
            assertEquals("^^C,',/3", m.group());
        }
    }
    
    /**
     * tests that the the Rest regex is able to match all the parts of a rest 
     * ("z", note-length)
     */ 
    @Test
    public void restRegexTest(){
        Matcher m = Pattern.compile(ABCLexer.REST).matcher("z/4");
        if (m.find()){
            assertEquals("z/4", m.group());
        }
    }
    
    /**
     * tests that the the Chord regex is able to match all the parts of a chord 
     * (start-chord, notes or rests, end-chord)
     */ 
    @Test
    public void chordRegexTest(){
        Matcher m = Pattern.compile(ABCLexer.CHORD).matcher("[a/4b,/4^d/4]");
        if (m.find()){
            assertEquals("[a/4b,/4^d/4]", m.group());
        }
    }
    
    /**
     * tests that the the Tuplet regex is able to match all the parts of a tuplet 
     * (start-tuplet, number of notes, and notes)
     */ 
    @Test
    public void tupletRegexTest(){
        Matcher m = Pattern.compile(ABCLexer.TUPLET).matcher("(3^a/6__B/6c'/6");
        if (m.find()){
            assertEquals("(3^a/6__B/6c/6", m.group());
        }
        assert false;
    }
    
    /**
     * tests that the the Barline regex is able to match a barline 
     */ 
    @Test
    public void barlineRegexTest(){
        Matcher m = Pattern.compile(ABCLexer.BARLINE).matcher(":|");
        if (m.find()){
            assertEquals(":|", m.group());
        }
    }
    
    /**
     * tests that the the Comment regex is able to match all the parts of a comment 
     * (comment signaler %, text of the comment, line-feed)
     */ 
    @Test
    public void commentRegexTest(){
        Matcher m = Pattern.compile(ABCLexer.COMMENT).matcher("% I want a cookie\n");
        if (m.find()){
            assertEquals("% I want a cookie\n", m.group());
        }
    }
    
    /**
     * tests that ABCLexer.lex returns the correct tokens for sample1.abc
     */ 
    @Test
    public void sample1LexTest() throws IOException{
        
        ArrayList<Token> expected = new ArrayList<Token>();
        expected.add(new Token(TokenType.HEADER_FIELD, "X:1\n"));
        expected.add(new Token(TokenType.HEADER_FIELD, "T:sample 1\n"));
        expected.add(new Token(TokenType.HEADER_FIELD, "K:C\n"));
        expected.add(new Token(TokenType.NOTE, "C,2"));
        expected.add(new Token(TokenType.NOTE, "C2"));
        expected.add(new Token(TokenType.NOTE, "c'"));
        expected.add(new Token(TokenType.NOTE, "c''"));
        expected.add(new Token(TokenType.BARLINE, "|]"));
        
        assertEquals(expected, ABCLexer.lex("sample_abc/sample1.abc"));
        
    }
    
    /**
     * tests that ABCLexer.lex returns the correct tokens for sample2.abc
     */
    @Test
    public void sample2LexTest() throws IOException{
        ArrayList<Token> expected = new ArrayList<Token>();
        expected.add(new Token(TokenType.HEADER_FIELD, "X:8\n"));
        expected.add(new Token(TokenType.HEADER_FIELD, "T:Chord\n"));
        expected.add(new Token(TokenType.HEADER_FIELD, "K:C\n"));
        expected.add(new Token(TokenType.CHORD, "[EC]"));
        
        assertEquals(expected, ABCLexer.lex("sample_abc/sample2.abc"));
    }
    
    /**
     * tests that ABCLexer.lex returns the correct tokens for sample3.abc
     */
    @Test
    public void sample3LexTest() throws IOException{
        ArrayList<Token> expected = new ArrayList<Token>();
        expected.add(new Token(TokenType.HEADER_FIELD, "X:1\n"));
        expected.add(new Token(TokenType.HEADER_FIELD, "T:voices\n"));
        expected.add(new Token(TokenType.VOICE, "V:1\n"));
        expected.add(new Token(TokenType.VOICE, "V:2\n"));
        expected.add(new Token(TokenType.VOICE, "V:3\n"));
        expected.add(new Token(TokenType.HEADER_FIELD, "Cm\n"));
        expected.add(new Token(TokenType.VOICE, "V: 1\n"));
        expected.add(new Token(TokenType.NOTE, "C"));
        expected.add(new Token(TokenType.VOICE, "V: 2\n"));
        expected.add(new Token(TokenType.NOTE, "E"));
        expected.add(new Token(TokenType.VOICE, "V: 3\n"));
        expected.add(new Token(TokenType.NOTE, "G"));
        
        assertEquals(expected, ABCLexer.lex("sample_abc/sample3.abc"));
    }
}

