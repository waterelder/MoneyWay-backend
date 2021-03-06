package xyz.trackbuck.domain.model.cash;

import lombok.Getter;
import lombok.Setter;
import xyz.trackbuck.domain.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by lex on 30.09.16.
 */
@Setter
@Getter
@Entity
@Table(name="Banknotes")
public class Banknote extends BaseEntity {
    private Integer currency;
    private String name;
    private String label;
    @ManyToOne
    @JoinColumn(name = "cashType")
    private BanknoteType banknoteType;

}
