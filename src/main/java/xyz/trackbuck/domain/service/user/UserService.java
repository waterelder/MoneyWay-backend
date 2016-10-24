package xyz.trackbuck.domain.service.user;

import org.springframework.http.ResponseEntity;
import xyz.trackbuck.domain.model.user.UnconfirmedUser;
import xyz.trackbuck.security.AuthResponse;

import javax.mail.internet.AddressException;

/**
 * Created by lex on 08.10.16.
 */
public interface UserService {
    Boolean checkExistenceByUsername(String username);
    Boolean checkExistenceByEmail(String email);
    Boolean register(UnconfirmedUser user) throws Exception;
    AuthResponse confirm(String key) throws Exception;
    void resentConfirmation(UnconfirmedUser user) throws AddressException, Exception;
}
