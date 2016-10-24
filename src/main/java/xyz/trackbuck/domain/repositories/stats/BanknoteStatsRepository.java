package xyz.trackbuck.domain.repositories.stats;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import xyz.trackbuck.domain.model.stats.BanknoteStat;
import xyz.trackbuck.domain.model.stats.Summ;

import java.util.Collection;

/**
 * Created by lex on 24.10.16.
 */
public interface BanknoteStatsRepository extends CrudRepository<BanknoteStat, Integer> {

}