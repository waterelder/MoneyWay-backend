package xyz.trackbuck.domain.repositories.user;

import org.springframework.data.repository.CrudRepository;
import xyz.trackbuck.domain.model.user.UnconfirmedUser;

import java.util.Collection;

/**
 * Created by lex on 09.10.16.
 */
public interface UnconfirmedUserRepository extends CrudRepository<UnconfirmedUser, Integer> {

    Collection<UnconfirmedUser> findByEmailOrUsername(String email, String username);

    UnconfirmedUser getByHash(String hash);
}
