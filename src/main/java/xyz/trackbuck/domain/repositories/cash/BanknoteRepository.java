package xyz.trackbuck.domain.repositories.cash;

import org.springframework.data.repository.CrudRepository;
import xyz.trackbuck.domain.model.cash.Banknote;
import xyz.trackbuck.domain.model.cash.BanknoteType;

import java.util.Collection;

/**
 * Created by lex on 30.09.16.
 */
public interface BanknoteRepository extends CrudRepository<Banknote, Integer> {
    Collection<Banknote> findByBanknoteTypeCode(String typeName);
}
