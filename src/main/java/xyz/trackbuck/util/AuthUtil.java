package xyz.trackbuck.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;
import xyz.trackbuck.security.AuthType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by lex on 07.10.16.
 */
public class AuthUtil {
    public static AuthType getAuthType(String credintial){
       return (StringUtils.countOccurrencesOf(credintial, "@") > 0)? AuthType.EMAIL: AuthType.USERNAME;


    }
    public static Collection<String> getRoleNames(Collection<? extends GrantedAuthority> authorities){
        List<String> roleNames = new ArrayList<>();
        authorities.forEach(s -> {
            roleNames.add(s.getAuthority());
        });
        return roleNames;
    };
}
