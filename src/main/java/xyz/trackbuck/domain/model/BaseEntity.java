package xyz.trackbuck.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lex on 26.09.16.
 */
@MappedSuperclass
@Setter
@Getter

public abstract class BaseEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, unique = true)
    private Integer id;


}
