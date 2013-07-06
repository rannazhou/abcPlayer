package player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ABCLexer {
    private final Matcher matcher;
    private final String abcString;
    
    // Used for meter and default length
    public static final String STRICT_FRACTION = "((\\d+)\\/(\\d+))";
    public static final String DURATION_FRACTION = "(((\\d+)?(\\/)(\\d+)?)|(\\d+))";
    
    // Regex matching the next token.
    public static final String NOTE = "((\\^\\^|\\^|\\_\\_|\\_|\\=)?([A-Ga-g])(,|')*"+DURATION_FRACTION+"?)";
    public static final String BARLINE = "((\\|\\|)|(\\[\\|)|(\\|\\])|(:\\|)|(\\|:)|(\\|))";
    public static final String REST = "(z"+DURATION_FRACTION+"?)";
    public static final String CHORD = "(\\[(" + NOTE + "|" + REST+ ")+" + "\\])";
    public static final String TUPLET = "(\\(\\d("+CHORD+"|"+NOTE+"|"+REST+")+\\s)";
    public static final String NthREPEAT = "(\\[(1|2))";
    
    public static final String FIELD_NUMBER = "(X:\\s*(\\d+))";
    public static final String FIELD_TITLE = "(T:\\s*(.+))";
    public static final String FIELD_COMPOSER = "(C:\\s*(.+))";
    public static final String FIELD_DEFAULT_LENGTH = "(L:\\s*"+STRICT_FRACTION+")";
    public static final String FIELD_METER = "(M:\\s*((C\\|)|(C)|"+STRICT_FRACTION+"))";
    public static final String FIELD_TEMPO = "(Q:\\s*(\\d+))";
    public static final String FIELD_KEY = "(K:\\s*([A-G])([#b]?)(m?))";
    public static final String FIELD_VOICE = "(V:\\s*(\\w+)\\n)";
    
    public static final String COMMENT = "(%.*\\n)";
    
    public static final Pattern TOKEN_REGEX 
        = Pattern.compile (
//             "^"  // anchors to the beginning of the match start, so that we don't skip any characters
                // Header field
                "((" +FIELD_NUMBER // FIELD_NUMBER
                    + "|"
                    + FIELD_TITLE //FIELD_TITLE
                    + "|"
                    + FIELD_COMPOSER//FIELD_COMPOSER
                    + "|"
                    + FIELD_DEFAULT_LENGTH //FIELD_LENGTH
                    + "|"
                    + FIELD_METER //FIELD_METER
                    + "|"
                    +  FIELD_TEMPO //FIELD_TEMPO
                    + "|"
                    + FIELD_KEY //FIELD_KEY
                    + ")\n)" 
                
                + "|"
                
                // Chord
                + CHORD
                + "|"
                
                //Tuplet
                + TUPLET
                + "|"
                
                // Voice
                + FIELD_VOICE
                + "|"
                
                 // Note
                + NOTE
                + "|"
                
                //Rest
                + REST
                + "|"
                
                // Nth repeat
                + NthREPEAT
                + "|"
                
                // Barline -the most confusing regex ever
                + BARLINE 
                + "|"
                
                 // Comment
                + COMMENT 
                + "|"
                
                 // End of file
                + "(\\n\\z)"
          );
    
    public ABCLexer(String file) throws IOException{
        this.abcString = readFileToString(file);
        this.matcher = TOKEN_REGEX.matcher(abcString);
    }
    
    /**
     * @return the next token for the expression matched by the matcher,
     * 
     */
    private Token next() {
        if (matcher.hitEnd()){
            return new Token(TokenType.EOF, "");
        }
        // Look for the next token
        if (!matcher.find()) {
            // No token found
            throw new AssertionError ("Syntax error at " + abcString.substring(matcher.end()));
        }
       
        // Get the part of the string that the regex matched,
        // and advance our state
        String value = matcher.group();
        
        // Looks for matches to the capturing groups for which we have TokenTypes.
        // Since matcher uses greedy matching and then advances the index of the string that
        // it is at, all the "sub"-capturing groups will not be matched anyway.
        for (int i: new int[] {1, 26, 45, 83, 85, 95, 102, 104, 111, 112}){
            if (matcher.group(i) != null) {
                
                //System.out.println("Group " + i + ", matches " + matcher.group(i));
                if (i == 1){
                    return new Token(TokenType.HEADER_FIELD, value);
                }
                
                else if (i == 26){
                    return new Token(TokenType.CHORD, value);
                }
                
                else if (i== 45){
                    return new Token(TokenType.TUPLET, value);
                }
                
                else if (i== 83){
                    return new Token(TokenType.VOICE, value);
                }
                
                else if (i== 85){
                    return new Token(TokenType.NOTE, value);
                }
                
                else if (i== 95){
                    return new Token(TokenType.REST, value);
                }
                
                else if (i == 104){
                    return new Token(TokenType.BARLINE, value);
                }
                
                else if (i == 102){
                    return new Token(TokenType.Nth_REPEAT, value);
                }
                
                else if (i == 111){
                    return new Token(TokenType.COMMENT, value);
                }
                
                else if (i == 112){
                    return new Token(TokenType.EOF, value);
                }
            }
        }
        // if we got here, there's a bug in the regex -- Matcher said we matched the 
        // expression, but it didn't match any of the parens
        throw new AssertionError("shouldn't get here");
    }
    
    
    /**
     * @param string abcFile with the name of the ABC file
     * 
     * @return a list tokens for the ABCFile (only returns types enumerated in TokenType.java)
     */
    public static ArrayList<Token> lex (String abcFile) throws IOException{
        ArrayList<Token> tokens = new ArrayList<Token>();
        ABCLexer lexer = new ABCLexer(abcFile);
		try{
            for (Token tok = lexer.next(); tok.getType() != TokenType.EOF; tok = lexer.next()) {
                tokens.add(tok);
            }
        } catch (IllegalStateException e) {}
        
        return tokens;
    }
    
    /**
     * @param string filename with the name of the file
     * 
     * @return a string with all the contents of the file, including newline characters
     */
    private static String readFileToString(String fileName) throws IOException{
        File file = new File(fileName);
        FileReader fr = null;
        BufferedReader br = null;
        
        StringBuilder builder = new StringBuilder();
        String line = "";
        
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            
            while ((line = br.readLine()) != null) {
                builder.append(line+'\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fr.close();
            br.close();
        }
        
        return builder.toString();
    }
    
    
}
