package ods.raidplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaidSearchFilterDTO implements Serializable {
    private LocalDateTime startDateFilter;
    private LocalDateTime endDateFilter;
    private List<Long> groupIdsFilter;
    private Integer maxRankFilter;
}
