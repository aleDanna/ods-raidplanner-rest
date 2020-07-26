package ods.raidplanner.persistence.repository;

import ods.raidplanner.persistence.model.Character;
import ods.raidplanner.persistence.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    List<Subscription> findByCharacterIn(List<Character> list);

    Subscription findByRaidIdAndCharacterId(Long raidId, Long characterId);
}
