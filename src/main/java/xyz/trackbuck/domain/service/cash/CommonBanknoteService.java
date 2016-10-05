package xyz.trackbuck.domain.service.cash;

import xyz.trackbuck.domain.model.cash.Banknote;
import xyz.trackbuck.domain.model.cash.BanknoteType;

import java.util.Collection;

/**
 * Created by lex on 30.09.16.
 */
public interface CommonBanknoteService {

    Collection<BanknoteType> findAllBanknoteTypes();
    Collection<Banknote> findBanknotesByType(String typeName);
}
