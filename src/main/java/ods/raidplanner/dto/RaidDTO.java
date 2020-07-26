package ods.raidplanner.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RaidDTO extends ODSDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private RaidGroupDTO raidGroup;
    private List<SubscriptionDTO> subscriptions;

}
