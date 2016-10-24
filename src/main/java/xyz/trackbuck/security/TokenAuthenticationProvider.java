package xyz.trackbuck.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.AuthProvider;

/**
 * Created by lex on 08.10.16.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    TokenAuthenticationManager authenticationManager;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        return authenticationManager.authenticate(authentication);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
