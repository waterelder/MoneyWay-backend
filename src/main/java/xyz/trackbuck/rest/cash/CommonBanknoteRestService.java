package xyz.trackbuck.rest.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.trackbuck.domain.model.cash.Banknote;
import xyz.trackbuck.domain.model.cash.BanknoteType;
import xyz.trackbuck.domain.service.cash.CommonBanknoteService;

import java.util.Collection;

/**
 * Created by lex on 30.09.16.
 */
@RestController
@RequestMapping("/banknotes")
public class CommonBanknoteRestService {
    @Autowired
    CommonBanknoteService banknoteService;

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    ResponseEntity<Collection<BanknoteType>> findAllTypes(){
        return ResponseEntity.ok(banknoteService.findAllBanknoteTypes());
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    ResponseEntity<Collection<Banknote>> findBanknotesByType(@RequestParam(value = "code") String typeCode){
        return ResponseEntity.ok(banknoteService.findBanknotesByType(typeCode));
    }

}
