package xyz.trackbuck.rest.secured.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xyz.trackbuck.domain.model.cash.CashRecord;
import xyz.trackbuck.domain.model.user.User;
import xyz.trackbuck.domain.service.cash.CashRecordService;
import xyz.trackbuck.security.CustomUserDetails;
import xyz.trackbuck.security.TokenAuthentication;
import xyz.trackbuck.util.NotFoundSearchTypeException;
import xyz.trackbuck.util.ResponseUtil;

import java.util.Collection;

/**
 * Created by lex on 29.09.16.
 */

//TODO Обмазать запросы аспектом чтобы не обворачивать результаты ResponseEntity
@RestController
@CrossOrigin
@RequestMapping("/rest/cash")
@ResponseBody
public class CashRecordRestService {
    @Autowired
    CashRecordService cashRecordService;


    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/fullsearch")
    Collection<CashRecord> findByUserId(@RequestParam(value = "param") String searchType, @RequestParam(value = "value") Object param) {

        SearchType type = SearchType.getType(searchType);
        Collection<CashRecord> resp = null;

            switch (type) {
                case CASHID:
                    resp = cashRecordService.findByCashId(param.toString());
                    break;
                case USERID:
                    resp = cashRecordService.findByUserId(Integer.valueOf(param.toString()));
                    break;
                case LOCALITY:
                    resp = cashRecordService.findByLocality(param.toString());
                    break;
                case REGION:
                    resp = cashRecordService.findByRegion(param.toString());
                    break;
            }


        return resp;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("")
    Collection<CashRecord> findMyCashRecords() {
        return cashRecordService.findUsersCash();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "" ,method = RequestMethod.POST)
    CashRecord saveCashRecords(@RequestBody CashRecord record) {
        return cashRecordService.save(record);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/search")
    Collection<CashRecord> findCashRecordsById(@RequestParam(value = "cashId") String cashId) {

        return cashRecordService.findExtendedByCashId(cashId);
    }


    private enum SearchType {
        USERID("userId"), CASHID("cashId"), LOCALITY("locality"), REGION("region");
        private final String text;

        SearchType(String text) {
            this.text = text;
        }

        static public SearchType getType(String pType) {
            for (SearchType type : SearchType.values()) {
                if (type.getText().equals(pType)) {
                    return type;
                }
            }
            throw new NotFoundSearchTypeException("unknown type");
        }

        public String getText() {
            return text;
        }
    }

}
