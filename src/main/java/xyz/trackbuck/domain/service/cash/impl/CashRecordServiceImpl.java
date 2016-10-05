package xyz.trackbuck.domain.service.cash.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.trackbuck.domain.model.cash.CashRecord;
import xyz.trackbuck.domain.repositories.cash.CashRecordRepository;
import xyz.trackbuck.domain.service.cash.CashRecordService;

import java.util.Collection;
import java.util.Date;

/**
 * Created by lex on 29.09.16.
 */
@Service
public class CashRecordServiceImpl implements CashRecordService {
    @Autowired
    CashRecordRepository recordRepository;

    @Override
    public Collection<CashRecord> findByUserId(Integer userId) {
        return recordRepository.findByUserId(userId);
    }

    @Override
    public Collection<CashRecord> findByCashId(String cashId) {
        return recordRepository.findByCashIdOrderByDatetime(cashId);
    }

    @Override
    public Collection<CashRecord> findByLocality(String locality) {
        return recordRepository.findByLocality(locality);
    }

    @Override
    public Collection<CashRecord> findByRegion(String region) {
        return recordRepository.findByRegion(region);
    }

    @Override
    public Collection<CashRecord> findByLatAndLon(String lat, String lon) {
        return recordRepository.findByLatAndLon(lat, lon);
    }

    @Override
    public Collection<CashRecord> saveAndGetSimmular(CashRecord cashRecord) {
        cashRecord.setDatetime(new Date());
        recordRepository.save(cashRecord);
        return recordRepository.findByCashIdOrderByDatetime(cashRecord.getCashId());
    }
}
