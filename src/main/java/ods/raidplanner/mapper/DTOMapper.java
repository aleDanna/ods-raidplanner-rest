package ods.raidplanner.mapper;

import ods.raidplanner.dto.*;
import ods.raidplanner.persistence.model.*;
import ods.raidplanner.persistence.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DTOMapper {

    ODSDTO toDto(ODSEntity entity);

    ODSEntity toEntity(ODSDTO dto);

    RaidGroupDTO toDto(RaidGroup entity);

    Raid toEntity(RaidDTO dto);

    RaidDTO toDto(Raid entity);

    UserDTO toDto(User entity);

    User toEntity(UserDTO dto);

    @Mapping(target = "raid", ignore = true)
    Subscription toEntity(SubscriptionDTO dto);

    @Mapping(target = "raidId", source = "raid.id")
    SubscriptionDTO toDto(Subscription entity);

    CharacterRoleDTO toDto(CharacterRole entity);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    Character toEntity(CharacterDTO dto);

    @Mapping(target = "userId", source = "user.id")
    CharacterDTO toDto(Character entity);

}
