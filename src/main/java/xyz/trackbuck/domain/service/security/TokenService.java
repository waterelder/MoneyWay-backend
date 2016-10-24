package xyz.trackbuck.domain.service.security;

import xyz.trackbuck.security.AuthType;
import xyz.trackbuck.security.TokenObject;

public interface TokenService {
    TokenObject getToken(String credintial, String password, AuthType type) throws Exception;
}