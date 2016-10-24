package xyz.trackbuck.rest.unsecured.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.trackbuck.domain.model.common.BooleanResponse;
import xyz.trackbuck.domain.model.user.UnconfirmedUser;
import xyz.trackbuck.domain.service.security.ExtendedUserDetailsService;
import xyz.trackbuck.domain.service.user.UserService;
import xyz.trackbuck.security.*;

/**
 * Created by lex on 07.10.16.
 */
@RestController
@CrossOrigin
@RequestMapping("auth")
public class AuthRestService {

    @Autowired
    private ExtendedUserDetailsService detailsService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    AuthResponse auth(@RequestBody AuthRequest request) throws Exception {
        return detailsService.getAuthResponse(request);
    }

    @ResponseBody
    @RequestMapping(value = "/check/email")
    BooleanResponse checkEmail(@RequestParam(value = "p") String email) throws Exception {
        return new BooleanResponse(userService.checkExistenceByEmail(email));
    }
    @ResponseBody
    @RequestMapping(value = "/check/login")
    BooleanResponse checkLogin(@RequestParam(value = "p") String username) throws Exception {
        return new BooleanResponse(userService.checkExistenceByUsername(username));
    }
    @ResponseBody
    @RequestMapping(value="/register", method = RequestMethod.POST)
    BooleanResponse register(@RequestBody UnconfirmedUser user) throws Exception {
        Boolean status = userService.register(user);
        return new BooleanResponse(status);

    }
    @ResponseBody
    @RequestMapping(value="/confirm")
    AuthResponse confirm(@RequestParam(value = "hash")String key) throws Exception {
        return userService.confirm(key);
    }

    @RequestMapping(value="/resent", method = RequestMethod.POST)
    void resent(@RequestBody UnconfirmedUser user) throws Exception {
       userService.resentConfirmation(user);
    }



}
