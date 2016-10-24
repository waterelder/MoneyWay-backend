package xyz.trackbuck.security;

/**
 * Created by lex on 07.10.16.
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.trackbuck.domain.service.security.ExtendedUserDetailsService;

import java.util.*;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {

    @Autowired
    private ExtendedUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication instanceof TokenAuthentication) {
                TokenAuthentication readyTokenAuthentication = processAuthentication((TokenAuthentication) authentication);
                return readyTokenAuthentication;
            } else {
                authentication.setAuthenticated(false);
                return authentication;
            }
        } catch (Exception ex) {
            if (ex instanceof AuthenticationServiceException)
                throw new BadCredentialsException(ex.getMessage());
        }
        return authentication;
    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationException {
        if (authentication.getToken() == null){
            throw new AuthenticationServiceException("Token dismissed");
        }
        String token = authentication.getToken();
        String key = AuthConfig.KEY;
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }
        if (claims.get("token_expiration_date", Long.class) == null)
            throw new AuthenticationServiceException("Invalid token");
        Date expiredDate = new Date(claims.get("token_expiration_date", Long.class));
        if (expiredDate.after(new Date()))
            return buildFullTokenAuthentication(authentication, claims);
        else
            throw new AuthenticationServiceException("Token expired date error");
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, DefaultClaims claims) {
        String username = claims.get("username", String.class);
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByCreditial(username);


        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
        return new TokenAuthentication(authentication.getToken(), authorities, true, user);

    }
}
