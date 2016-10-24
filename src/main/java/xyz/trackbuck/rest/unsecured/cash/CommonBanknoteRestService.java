package xyz.trackbuck.rest.unsecured.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.trackbuck.domain.model.cash.Banknote;
import xyz.trackbuck.domain.model.cash.BanknoteType;
import xyz.trackbuck.domain.service.cash.CommonBanknoteService;

import java.util.Collection;

/**
 * Created by lex on 30.09.16.
 */
@RestController
@CrossOrigin
@RequestMapping("/banknotes")
public class CommonBanknoteRestService {
    @Autowired
    CommonBanknoteService banknoteService;

    @RequestMapping(value = "/types", method = RequestMethod.GET)

    @ResponseBody
    Collection<BanknoteType> findAllTypes(){
        return banknoteService.findAllBanknoteTypes();
    }
    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    Collection<Banknote> findBanknotesByType(@RequestParam(value = "code") String typeCode){
        return banknoteService.findBanknotesByType(typeCode);
    }

}
