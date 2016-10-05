package xyz.trackbuck.domain.repositories.cash;

import org.springframework.data.repository.CrudRepository;
import xyz.trackbuck.domain.model.cash.BanknoteType;

import java.util.Collection;

/**
 * Created by lex on 30.09.16.
 */
public interface BanknoteTypeRepository extends CrudRepository<BanknoteType, Integer> {
    Collection<BanknoteType> findAll();

}
