package xyz.trackbuck.util;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by lex on 07.10.16.
 */
public class EmailNotFoundExceprion extends AuthenticationException {
    public EmailNotFoundExceprion(String msg) {
        super(msg);
    }

    public EmailNotFoundExceprion(String msg, Throwable t) {
        super(msg, t);
    }
}