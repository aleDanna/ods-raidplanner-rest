package ods.raidplanner.dto;

import lombok.Data;
import ods.raidplanner.enums.RaidGroupEnum;

@Data
public class RaidGroupDTO extends ODSDTO {

    private RaidGroupEnum name;
    private Integer rank;
    private String imageName;

}
