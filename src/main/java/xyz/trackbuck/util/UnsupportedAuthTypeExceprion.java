package xyz.trackbuck.util;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by lex on 07.10.16.
 */
public class UnsupportedAuthTypeExceprion extends AuthenticationException {
    public UnsupportedAuthTypeExceprion(String msg) {
        super(msg);
    }

    public UnsupportedAuthTypeExceprion(String msg, Throwable t) {
        super(msg, t);
    }
}