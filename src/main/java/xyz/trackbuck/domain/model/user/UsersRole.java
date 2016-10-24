package xyz.trackbuck.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import xyz.trackbuck.domain.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by lex on 07.10.16.
 */
@Entity
@Getter
@Setter
public class UsersRole extends BaseEntity implements GrantedAuthority {


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    @Column(name = "userId")
    private Integer userId;

    public UsersRole() {
    }

    public UsersRole(Integer userId, Role role) {
        this.role = role;
        this.userId = userId;
    }



    @Override
    public String getAuthority() {
        return this.role.getName();
    }
}
