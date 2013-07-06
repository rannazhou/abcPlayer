package player;

/**
 * A Token is an immutable value representing a token
 * in ABC.
 *
 */
public class Token {
    private final TokenType type;
    private final String value;
    
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    
    public TokenType getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }
    
    public String toString() {
        return "Token "+value;
    }

    // x.equals(y) ==> x.hashcode() == y.hashcode()
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Token other = (Token) obj;
        if (type != other.type){
            return false;
        }
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value)) return false;
        
        return true;
    }
    
    
}
