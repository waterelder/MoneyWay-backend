package xyz.trackbuck.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lex on 07.10.16.
 */
@Getter
@Setter
public class AuthRequest {

    private String credintial;
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String credintial, String password) {
        this.credintial = credintial;
        this.password = password;
    }

}
