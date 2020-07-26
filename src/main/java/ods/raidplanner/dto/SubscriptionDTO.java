package ods.raidplanner.dto;

import lombok.Data;

@Data
public class SubscriptionDTO extends ODSDTO {

    private CharacterDTO character;
    private Long raidId;
    private Integer groupNumber;
}
