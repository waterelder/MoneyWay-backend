package xyz.trackbuck.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import xyz.trackbuck.domain.model.user.User;

import java.util.Collection;

/**
 * Created by lex on 07.10.16.
 */

@Getter
@Setter
public class AuthResponse extends TokenObject {

    private CustomUserDetails user;


    public AuthResponse(TokenObject tokenObject, CustomUserDetails user){
        super(tokenObject);
        this.user = user;
    }

}
