package xyz.trackbuck.domain.model.cash;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.*;
import xyz.trackbuck.domain.model.BaseEntity;
import xyz.trackbuck.domain.model.user.User;

import javax.persistence.*;
import javax.persistence.Temporal;
import java.util.Date;

/**
 * Created by lex on 26.09.16.
 */
@Entity
@Table(name = "CashRecords")
@Getter
@Setter
public class CashRecord extends BaseEntity {

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date datetime;

    @Column(nullable = false)
    String cashId;

    @ManyToOne
    @JoinColumn(name = "banknote")
    Banknote banknote;


    @ManyToOne
    @JoinColumn(name = "user")
    User user;

    @Lob
    String comment;
    String address;
    String region;
    Float lat;
    Float lon;
    String locality;


}
