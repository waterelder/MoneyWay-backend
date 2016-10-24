package xyz.trackbuck.rest.secured.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.trackbuck.domain.model.cash.CashRecord;
import xyz.trackbuck.domain.model.user.User;
import xyz.trackbuck.util.ResponseUtil;

import java.util.Collection;

/**
 * Created by lex on 09.10.16.
 */
@RestController
@CrossOrigin
@RequestMapping("/rest/user/")
public class UserRestService {

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping("/")
    @ResponseBody
    User getMe() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
