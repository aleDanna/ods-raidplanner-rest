package ods.raidplanner.persistence.repository;

import ods.raidplanner.persistence.model.Character;
import ods.raidplanner.persistence.model.CharacterRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRoleRepository extends CrudRepository<CharacterRole, Long> {

}
