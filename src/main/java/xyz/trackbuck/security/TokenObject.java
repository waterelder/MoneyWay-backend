package xyz.trackbuck.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lex on 07.10.16.
 */

@Getter
@Setter
public class TokenObject  {
    protected String token;

    public TokenObject(String token) {
        this.token = token;
    }

    public  TokenObject(){

    }

    public TokenObject(TokenObject tokenObject){
        this.token = tokenObject.token;
    }


    @Override
    public String toString() {
        return this.token;
    }
}
