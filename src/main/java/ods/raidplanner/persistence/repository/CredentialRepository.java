package ods.raidplanner.persistence.repository;

import ods.raidplanner.persistence.model.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long> {

    Credential findByUsername(String username);

    Credential findByUsernameAndPassword(String username, String password);

}
