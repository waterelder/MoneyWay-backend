package xyz.trackbuck.domain.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.trackbuck.domain.service.security.TokenService;
import xyz.trackbuck.security.*;
import xyz.trackbuck.domain.model.user.User;
import xyz.trackbuck.domain.model.user.UsersRole;
import xyz.trackbuck.domain.repositories.user.UserRepository;
import xyz.trackbuck.domain.repositories.user.UsersRoleRepository;
import xyz.trackbuck.domain.service.security.ExtendedUserDetailsService;
import xyz.trackbuck.util.AuthUtil;
import xyz.trackbuck.util.EmailNotFoundExceprion;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by lex on 07.10.16.
 */
@Service
class ExtendedUsersDetailsServiceImpl implements ExtendedUserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UsersRoleRepository usersRoleRepository;

    @Autowired
    TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsernameIgnoreCase(username);
        if (null == user) {
            throw new AuthenticationCredentialsNotFoundException("");
        }
        return new CustomUserDetails(user, this.getUsersRoles(user.getId()));

    }
    @Override
    public UserDetails loadUserByEmail(String email) throws EmailNotFoundExceprion {
        User user = userRepository.getByEmailIgnoreCase(email);
        if (null == user) {
            throw new AuthenticationCredentialsNotFoundException("");
        }
        return new CustomUserDetails(user, this.getUsersRoles(user.getId()));
    }

    @Override
    public UserDetails loadUserByCreditial(String creditial){
        if (AuthUtil.getAuthType(creditial) == AuthType.USERNAME){
            return this.loadUserByUsername(creditial);
        }else{
            return this.loadUserByEmail(creditial);
        }
    }

    @Override
    public AuthResponse getAuthResponse(AuthRequest request) throws Exception {
        TokenObject tokenObject = tokenService.getToken(request.getCredintial(), request.getPassword(), AuthUtil.getAuthType(request.getCredintial()));
        CustomUserDetails details = (CustomUserDetails) this.loadUserByCreditial(request.getCredintial());
        return new AuthResponse(tokenObject, details);
    }


    private Collection<UsersRole> getUsersRoles(Integer userId) {
        return usersRoleRepository.findByUserId(userId);
    }
}
