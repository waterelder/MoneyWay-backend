package xyz.trackbuck.domain.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import xyz.trackbuck.security.AuthRequest;
import xyz.trackbuck.security.AuthResponse;
import xyz.trackbuck.util.EmailNotFoundExceprion;

/**
 * Created by lex on 07.10.16.
 */
public interface ExtendedUserDetailsService extends UserDetailsService {
    UserDetails loadUserByEmail(String email) throws EmailNotFoundExceprion;
    UserDetails loadUserByCreditial(String creditial) throws EmailNotFoundExceprion;
    AuthResponse getAuthResponse(AuthRequest request) throws Exception;


}
