package xyz.trackbuck.domain.model.cash;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import xyz.trackbuck.domain.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lex on 26.09.16.
 */
@Setter
@Getter
@Entity
@Table(name="BanknoteTypes")
public class BanknoteType extends BaseEntity{
    String name;
    String code;
}
