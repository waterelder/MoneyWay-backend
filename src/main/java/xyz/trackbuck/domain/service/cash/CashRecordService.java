package xyz.trackbuck.domain.service.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.trackbuck.domain.model.cash.CashRecord;
import xyz.trackbuck.domain.repositories.cash.CashRecordRepository;

import java.util.Collection;

/**
 * Created by lex on 29.09.16.
 */

public interface CashRecordService {


    Collection<CashRecord> findByUserId(Integer userId);
    Collection<CashRecord> findByCashId(String cashId);
    Collection<CashRecord> findByLocality(String locality);
    Collection<CashRecord> findByRegion(String region);
    Collection<CashRecord> findByLatAndLon(String lat, String lon);
    Collection<CashRecord> saveAndGetSimmular(CashRecord cashRecord);

}
