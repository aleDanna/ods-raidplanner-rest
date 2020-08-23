package ods.raidplanner.service;

import ods.raidplanner.dto.RaidDTO;
import ods.raidplanner.dto.RaidGroupDTO;
import ods.raidplanner.dto.RaidSearchFilterDTO;
import ods.raidplanner.dto.SubscriptionDTO;
import ods.raidplanner.exceptions.NotFoundException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.mapper.DTOMapper;
import ods.raidplanner.persistence.model.Character;
import ods.raidplanner.persistence.model.Raid;
import ods.raidplanner.persistence.model.Subscription;
import ods.raidplanner.persistence.repository.CharacterRepository;
import ods.raidplanner.persistence.repository.RaidGroupRepository;
import ods.raidplanner.persistence.repository.RaidRepository;
import ods.raidplanner.persistence.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RaidService {

    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private RaidRepository raidRepository;

    @Autowired
    private RaidGroupRepository raidGroupRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public List<RaidGroupDTO> getRaidGroups() throws ODSException {
        try {
            List<RaidGroupDTO> result = new ArrayList<>();
            raidGroupRepository.findAll().forEach(raid -> result.add(dtoMapper.toDto(raid)));
            return result;
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public RaidDTO saveRaid(RaidDTO raid, boolean recurrent) throws ODSException {
        try {
            Raid result = raidRepository.save(dtoMapper.toEntity(raid));
            if (recurrent) {
                int month = raid.getStartDate().getMonth().getValue();
                while (month == raid.getStartDate().getMonth().getValue()) {
                    raid.setStartDate(raid.getStartDate().plusDays(7));
                    raid.setEndDate(raid.getEndDate().plusDays(7));
                    if (month == raid.getStartDate().getMonth().getValue()) {
                        raidRepository.save(dtoMapper.toEntity(raid));
                    }
                }
            }
            return dtoMapper.toDto(result);
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public void deleteEvent(Long eventId) throws ODSException {
        try {
            raidRepository.deleteById(eventId);
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public void updateSubscriptions(List<SubscriptionDTO> subscriptions) throws ODSException {
        try {
            for (SubscriptionDTO dto : subscriptions) {
                Subscription subscription = dtoMapper.toEntity(dto);
                subscription.setId(dto.getId());    //TODO investigate why it is not auto set?
                Raid raid = raidRepository.findById(dto.getRaidId()).orElse(null);
                subscription.setRaid(raid);
                subscriptionRepository.save(subscription);
            }
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public List<RaidDTO> getRaidsByFilter(RaidSearchFilterDTO filters) throws ODSException {
        try {
            List<Raid> list = raidRepository.findAll(RaidRepository.getByFilters(
                    filters.getStartDateFilter(),
                    filters.getEndDateFilter(),
                    filters.getGroupIdsFilter(),
                    filters.getMaxRankFilter()));
            return list.stream().map(dtoMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public List<RaidDTO> getSubscribedRaids(Long userId) throws ODSException {
        try {
            List<Character> characters = characterRepository.findByUserId(userId);
            return subscriptionRepository.findByCharacterIn(characters)
                    .stream()
                    .map(subscription -> dtoMapper.toDto(subscription.getRaid()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public SubscriptionDTO subscribe(Long raidId, Long characterId) throws ODSException {
        Raid raid = raidRepository.findById(raidId).orElse(null);
        Character character = characterRepository.findById(characterId).orElse(null);

        if (raid == null) {
            throw new NotFoundException(raidId, Raid.class);
        }
        if (character == null) {
            throw new NotFoundException(characterId, Character.class);
        }
        try {
            return dtoMapper.toDto(
                    subscriptionRepository.save(
                            Subscription.builder()
                                    .raid(raid)
                                    .groupNumber(0)
                                    .character(character).build()));
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public void unsubscribe(Long raidId, Long userId) throws ODSException {
        try {
            characterRepository.findByUserId(userId).forEach(character -> {
                Subscription subscription = subscriptionRepository.findByRaidIdAndCharacterId(raidId, character.getId());
                if (subscription != null) {
                    subscriptionRepository.delete(subscription);
                }
            });
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public RaidDTO getRaid(Long raidId) throws ODSException {
        Raid raid = raidRepository.findById(raidId).orElse(null);
        if (raid == null) {
            throw new NotFoundException(raidId, Raid.class);
        }
        try {
            return dtoMapper.toDto(raid);
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }
}
