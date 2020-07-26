package ods.raidplanner.persistence.repository;

import ods.raidplanner.persistence.model.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {

    List<Character> findByUserId(Long userId);
}
