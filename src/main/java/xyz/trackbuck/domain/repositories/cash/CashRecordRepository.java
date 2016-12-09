package xyz.trackbuck.domain.repositories.cash;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.trackbuck.domain.model.cash.CashRecord;
import xyz.trackbuck.domain.model.stats.BanknoteStat;

import java.util.Collection;

/**
 * Created by lex on 28.09.16.
 */

public interface CashRecordRepository extends CrudRepository<CashRecord, Integer> {

    Collection<CashRecord> findByUserId(Integer userId);
    Collection<CashRecord> findByCashIdOrderByDatetime(String cashId);
    Collection<CashRecord> findByCashIdAndUserIdOrderByDatetime(String cashId, Integer userId);
    Collection<CashRecord> findByLocality(String locality);
    Collection<CashRecord> findByRegion(String region);
    Collection<CashRecord> findByLatAndLon(String lat, String lon);

    @Query("SELECT COUNT(DISTINCT cr.cashId) FROM CashRecord cr")
    Integer getCountOfBancnotes();

    @Query("SELECT SUM(bk.currency) FROM CashRecord cr LEFT JOIN cr.banknote bk")
    Integer countSumm();


    @Query("SELECT cr.cashId, count(cr.id)  FROM CashRecord cr group by(cr.cashId) ORDER BY COUNT(cr.cashId) DESC")
    Page<Object> countRecordsStat(Pageable pageable);

    @Query("SELECT usr.username, count(cr.id)  FROM CashRecord cr LEFT JOIN cr.user usr WHERE cr.user IS NOT NULL group by(usr.username) ORDER BY COUNT(cr.cashId) DESC")
    Page<Object> countUsersStat(Pageable pageable);

    @Query("SELECT cr.locality, count(cr.id)  FROM CashRecord cr WHERE cr.locality IS NOT NULL group by(cr.locality) ORDER BY COUNT(cr.cashId) DESC")
    Page<Object> countLocalityStat(Pageable pageable);
}
