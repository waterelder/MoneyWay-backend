package xyz.trackbuck.domain.model.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import xyz.trackbuck.domain.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

/**
 * Created by lex on 26.09.16.
 */
@Entity
@Getter
@Setter
@Table(name="Roles")
public class Role extends BaseEntity{
    String name;

}
