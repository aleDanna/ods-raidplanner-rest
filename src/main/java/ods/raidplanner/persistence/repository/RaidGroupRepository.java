package ods.raidplanner.persistence.repository;

import ods.raidplanner.persistence.model.RaidGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaidGroupRepository extends CrudRepository<RaidGroup, Long> {

}
