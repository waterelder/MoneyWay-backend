package xyz.trackbuck.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.trackbuck.domain.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import xyz.trackbuck.domain.model.cash.CashRecord;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by lex on 26.09.16.
 */
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @JsonIgnore
    private String email;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;


    @JsonIgnore
    private String password;

    //на будущее
    @JsonIgnore
    private String vkId;

    @JsonIgnore
    private String vkToken;

    @JsonIgnore
    private String fbId;

    @JsonIgnore
    private String fbToken;


    public User() {
    }

    public User(User user) {
        this.email = user.email;
        this.fbId = user.fbId;
        this.vkId = user.vkId;
        this.fbToken = user.fbToken;
        this.vkToken = user.vkToken;
        this.email = user.email;
        this.username = user.username;
        this.password = user.password;
        this.setId(user.getId());
        this.registrationDate = user.registrationDate;

    }

    public User(UnconfirmedUser user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }




    @Transient
    @Getter(AccessLevel.NONE)
    private Integer cashCount;


}
