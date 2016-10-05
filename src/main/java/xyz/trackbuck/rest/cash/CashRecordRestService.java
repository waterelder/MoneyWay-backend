package xyz.trackbuck.rest.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.trackbuck.domain.model.cash.CashRecord;
import xyz.trackbuck.domain.service.cash.CashRecordService;
import xyz.trackbuck.util.NotFoundSearchTypeException;
import xyz.trackbuck.util.ResponseUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lex on 29.09.16.
 */
@RestController
@RequestMapping("/cash")
public class CashRecordRestService {
    @Autowired
    CashRecordService cashRecordService;

    @RequestMapping("/search")
    ResponseEntity<Collection<CashRecord>> findByUserId(@RequestParam(value = "param") String searchType, @RequestParam(value = "value") Object param) {
        SearchType type = SearchType.getType(searchType);
        Collection<CashRecord> resp = null;
        try{
            switch (type) {
                case CASHID:
                    resp = cashRecordService.findByCashId(param.toString());
                    break;
                case USERID:
                    resp =cashRecordService.findByUserId(Integer.valueOf(param.toString()));
                    break;
                case LOCALITY:
                    resp = cashRecordService.findByLocality(param.toString());
                    break;
                case REGION:
                    resp = cashRecordService.findByRegion(param.toString());
                    break;
            }
        }catch (NotFoundSearchTypeException e){
            return ResponseUtil.getBadRequest(e.getMessage());
        }

        return ResponseUtil.getOkRequest(resp);
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.POST)
    ResponseEntity<Collection<CashRecord>> lookUp(@RequestBody CashRecord record) {
        return ResponseUtil.getOkRequest(cashRecordService.saveAndGetSimmular(record));
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
