package xyz.trackbuck.rest.unsecured.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.trackbuck.domain.model.cash.CashRecord;
import xyz.trackbuck.domain.model.stats.Summ;
import xyz.trackbuck.domain.service.cash.CashRecordService;
import xyz.trackbuck.util.ResponseUtil;

import java.util.Collection;

/**
 * Created by lex on 29.09.16.
 */
@RestController
@CrossOrigin
@RequestMapping("cash")
public class CashRestService {
    @Autowired
    CashRecordService cashRecordService;

    @RequestMapping(value = "/lookup", method = RequestMethod.POST)
    @ResponseBody
    Collection<CashRecord> lookUp(@RequestBody CashRecord record) {
        return cashRecordService.saveAndGetSimmular(record);
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Summ getCountOfBanknotes() {
            return new Summ(cashRecordService.getCountOfBanknotes());
    }


}
