package ods.raidplanner.dto;

import lombok.Data;

@Data
public class EventDTO {
    private boolean recurrent;
    private RaidDTO raid;
}
