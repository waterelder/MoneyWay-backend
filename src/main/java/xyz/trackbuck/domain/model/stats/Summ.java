package xyz.trackbuck.domain.model.stats;

import lombok.Getter;
import lombok.Setter;
import xyz.trackbuck.domain.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lex on 24.10.16.
 */
@Setter
@Getter
@Entity
@Table(name="Banknotes")
public class Summ extends BaseEntity {
    Integer count;

    public Summ(Integer integer) {
        count = integer;
    }
}