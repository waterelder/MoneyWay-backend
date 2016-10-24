package xyz.trackbuck.domain.model.stats;

import lombok.Getter;
import lombok.Setter;
import xyz.trackbuck.domain.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lex on 24.10.16.
 */
@Getter
@Setter
@Entity
@Table(name = "BanknoteStats")
public class BanknoteStat extends BaseEntity {
    String cashId;
    Integer count;
}
