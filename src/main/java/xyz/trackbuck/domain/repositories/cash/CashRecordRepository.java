package xyz.trackbuck.domain.repositories.cash;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.trackbuck.domain.model.cash.CashRecord;

import java.util.Collection;

/**
 * Created by lex on 28.09.16.
 */

public interface CashRecordRepository extends CrudRepository<CashRecord, Integer> {

    Collection<CashRecord> findByUserId(Integer userId);
    Collection<CashRecord> findByCashIdOrderByDatetime(String cashId);
    Collection<CashRecord> findByLocality(String locality);
    Collection<CashRecord> findByRegion(String region);
    Collection<CashRecord> findByLatAndLon(String lat, String lon);

}
