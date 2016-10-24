package xyz.trackbuck.domain.repositories.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.trackbuck.domain.model.user.UsersRole;

import java.util.Collection;

/**
 * Created by lex on 07.10.16.
 */
@Repository
public interface UsersRoleRepository extends CrudRepository<UsersRole, Integer> {
    Collection<UsersRole> findByUserId(Integer userId);
}
