package ods.raidplanner.persistence.repository;

import ods.raidplanner.persistence.model.Credential;
import ods.raidplanner.persistence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByCredential(Credential credential);

    User findByEsoUsername(String esoUsername);
}
