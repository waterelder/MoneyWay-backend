package xyz.trackbuck.domain.repositories.user;

import org.springframework.data.repository.CrudRepository;
import xyz.trackbuck.domain.model.user.User;

import java.util.Collection;

/**
 * Created by lex on 07.10.16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User getByUsernameIgnoreCase(String username);
    Collection<User> findAllByUsernameNotNull();
    User getByEmailIgnoreCase(String email);
}
