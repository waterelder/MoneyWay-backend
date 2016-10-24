package xyz.trackbuck.domain.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.trackbuck.domain.model.user.Role;
import xyz.trackbuck.domain.model.user.UnconfirmedUser;
import xyz.trackbuck.domain.model.user.User;
import xyz.trackbuck.domain.model.user.UsersRole;
import xyz.trackbuck.domain.repositories.user.UnconfirmedUserRepository;
import xyz.trackbuck.domain.repositories.user.UserRepository;
import xyz.trackbuck.domain.repositories.user.UsersRoleRepository;
import xyz.trackbuck.domain.service.common.RegisterMailService;
import xyz.trackbuck.domain.service.security.ExtendedUserDetailsService;
import xyz.trackbuck.domain.service.user.UserService;
import xyz.trackbuck.security.AuthRequest;
import xyz.trackbuck.security.AuthResponse;
import xyz.trackbuck.security.Roles;
import xyz.trackbuck.util.MiscUtils;

import javax.mail.internet.AddressException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lex on 08.10.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RegisterMailService mailService;

    @Autowired
    UnconfirmedUserRepository unconfirmedUserRepository;


    @Autowired
    private ExtendedUserDetailsService detailsService;

    @Autowired
    private UsersRoleRepository usersRoleRepository;


    @Override
    public Boolean checkExistenceByUsername(String username) {
        return repository.getByUsernameIgnoreCase(username) != null;
    }

    @Override
    public Boolean checkExistenceByEmail(String email) {
        return repository.getByEmailIgnoreCase(email) != null;
    }

    @Override
    public Boolean register(UnconfirmedUser user) throws Exception {
        if (!checkExistenceByEmail(user.getEmail()) && !checkExistenceByUsername(user.getUsername())) {
            if ((unconfirmedUserRepository.findByEmailOrUsername(user.getEmail(), user.getUsername()).size()) > 0) {
                return false;
            } else {
                user.setHash(MiscUtils.uuid());
                mailService.send(user);
                user.setCreationDate(new Date());
                unconfirmedUserRepository.save(user);
                return true;
            }

        } else {
            throw new Exception("User exist with same creditials");
        }
    }

    @Override
    public AuthResponse confirm(String key) throws Exception {
        UnconfirmedUser unconfirmedUser = unconfirmedUserRepository.getByHash(key);
        if (unconfirmedUser != null){
            User newUser = new User(unconfirmedUser);
            newUser.setRegistrationDate(new Date());
            newUser = repository.save(newUser);
            //// TODO: 09.10.16 переделать роли на енумы 
            UsersRole newUsersRole = new UsersRole(newUser.getId(), new Role(2, Roles.USER.toString()));
            usersRoleRepository.save(newUsersRole);
            unconfirmedUserRepository.delete(unconfirmedUser);
            return detailsService.getAuthResponse(new AuthRequest(newUser.getEmail(), newUser.getPassword()));
        }else{
            throw new Exception("Hash not found");
        }
    }

    @Override
    public void resentConfirmation(UnconfirmedUser user) throws Exception {
        List<UnconfirmedUser> unconfirmedUsers = (List<UnconfirmedUser>) unconfirmedUserRepository.findByEmailOrUsername(user.getEmail(), user.getUsername());
        if (unconfirmedUsers.size() > 0){
            mailService.send(unconfirmedUsers.get(0));
        }else{
            throw new Exception("Unconfirmed user not found");
        }


    }
}
