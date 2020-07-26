package ods.raidplanner.dto;

import lombok.Data;

@Data
public class CharacterDTO extends ODSDTO {

    private String name;
    private CharacterRoleDTO role;
    private Long userId;
}
