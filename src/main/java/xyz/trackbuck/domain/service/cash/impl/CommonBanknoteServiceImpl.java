package xyz.trackbuck.domain.service.cash.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.trackbuck.domain.model.cash.Banknote;
import xyz.trackbuck.domain.model.cash.BanknoteType;
import xyz.trackbuck.domain.repositories.cash.BanknoteRepository;
import xyz.trackbuck.domain.repositories.cash.BanknoteTypeRepository;
import xyz.trackbuck.domain.service.cash.CommonBanknoteService;

import java.util.Collection;

/**
 * Created by lex on 30.09.16.
 */
@Service
public class CommonBanknoteServiceImpl implements CommonBanknoteService {
    @Autowired
    BanknoteRepository banknoteRepository;
    @Autowired
    BanknoteTypeRepository banknoteTypeRepository;
    @Override
    public Collection<BanknoteType> findAllBanknoteTypes() {
        return banknoteTypeRepository.findAll();
    }

    @Override
    public Collection<Banknote> findBanknotesByType(String typeName) {
        return banknoteRepository.findByBanknoteTypeCode(typeName);
    }
}
