package ods.raidplanner.dto;

import lombok.Data;
import ods.raidplanner.enums.CharacterRoleEnum;

@Data
public class CharacterRoleDTO extends ODSDTO {

    private CharacterRoleEnum roleName;
}
