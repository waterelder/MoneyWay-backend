package xyz.trackbuck.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    String login;
    @JsonIgnore
    String email;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date registrationDate;
    @JsonIgnore
    String password;
    @JsonIgnore
    String address;
    @JsonIgnore
    String vkId;
    @JsonIgnore
    String vkToken;
    @JsonIgnore
    String fbId;
    @JsonIgnore
    String fbToken;
    @JsonIgnore
    Float lat;
    @JsonIgnore
    Float lon;
    @JsonIgnore
    String postalCode;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role")
    Role role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    Collection<CashRecord> records;

}
