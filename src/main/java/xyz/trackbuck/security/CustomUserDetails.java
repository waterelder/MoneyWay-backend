package xyz.trackbuck.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import xyz.trackbuck.domain.model.user.User;
import xyz.trackbuck.domain.model.user.UsersRole;

import java.util.Collection;
import java.util.List;

/**
 * Created by lex on 07.10.16.
 */
public class CustomUserDetails extends User implements UserDetails {

    private Collection<UsersRole> usersRoles;

    public CustomUserDetails(User user, Collection<UsersRole> usersRoles){
        super(user);
        this.usersRoles  = usersRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.usersRoles;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
