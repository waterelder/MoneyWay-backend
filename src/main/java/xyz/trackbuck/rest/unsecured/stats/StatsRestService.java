package xyz.trackbuck.rest.unsecured.stats;

import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import xyz.trackbuck.domain.model.stats.Summ;
import xyz.trackbuck.domain.repositories.cash.CashRecordRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by lex on 29.09.16.
 */
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("stats")
public class StatsRestService {
    @Autowired
    CashRecordRepository recordRepository;


    @RequestMapping(value = "/summ", method = RequestMethod.GET)
    Summ lookUp() {
        return new Summ(recordRepository.countSumm());
    }

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    //TODO Вынести в сервис, отрефакторить
    public Page<Object> getStatsOfRecords() {

        Page<Object> objects =  recordRepository.countRecordsStat(new PageRequest(0,5));
        objects.forEach(o -> {
            if (((String) ((Object[]) o)[0]).length() > 7)
            ((Object[]) o)[0] = ((String) ((Object[]) o)[0]).substring(0,7) + "***";
        });
        return objects;
    }

    @RequestMapping(value = "/localities", method = RequestMethod.GET)
    public Page<Object> getStatsOfLocalities() {
        return recordRepository.countLocalityStat(new PageRequest(0,5));
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Page<Object> getStatsOfUsers() {
        return recordRepository.countUsersStat(new PageRequest(0,5));
    }


}
