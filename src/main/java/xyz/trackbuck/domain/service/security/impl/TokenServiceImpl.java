package xyz.trackbuck.domain.service.security.impl;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.trackbuck.security.AuthConfig;
import xyz.trackbuck.security.AuthType;
import xyz.trackbuck.security.CustomUserDetails;
import xyz.trackbuck.security.TokenObject;
import xyz.trackbuck.domain.service.security.ExtendedUserDetailsService;
import xyz.trackbuck.domain.service.security.TokenService;
import xyz.trackbuck.util.AuthUtil;

import java.util.*;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private ExtendedUserDetailsService userDetailsService;

    @Override
    public TokenObject getToken(String credintial, String password, AuthType type) throws Exception {
        if (credintial == null || password == null) {
            return null;
        }
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByCreditial(credintial);

        Map<String, Object> tokenData = new HashMap<>();

        if (password.equals(user.getPassword())) {
            tokenData.put("clientType", StringUtils.collectionToCommaDelimitedString(AuthUtil.getRoleNames(user.getAuthorities())));
            tokenData.put("userID", user.getId().toString());
            tokenData.put("username", user.getUsername());
            tokenData.put("token_create_date", new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            tokenData.put("token_expiration_date", calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            String key = AuthConfig.KEY;
            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
            return new TokenObject(token);
        } else {
            throw new AuthenticationCredentialsNotFoundException("");
        }
    }

}