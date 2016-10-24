package xyz.trackbuck.domain.model.user;

import lombok.Getter;
import lombok.Setter;
import xyz.trackbuck.domain.model.BaseEntity;
import xyz.trackbuck.domain.model.common.Messageble;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lex on 08.10.16.
 */
@Entity
@Table(name = "UnconfimedUsers")
@Getter
@Setter
public class UnconfirmedUser extends BaseEntity implements Messageble {
    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(nullable = false)
    private String hash;


    public UnconfirmedUser() {

    }

    public UnconfirmedUser(String username, String name, String email, String password, Date creationDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }
}
