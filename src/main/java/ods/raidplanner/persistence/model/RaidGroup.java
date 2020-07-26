package ods.raidplanner.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ods.raidplanner.enums.RaidGroupEnum;

import javax.persistence.*;

@Entity
@Table(name = "raid_groups")
@Data
@NoArgsConstructor
public class RaidGroup extends ODSEntity {

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RaidGroupEnum name;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    @Column(name = "image_name", nullable = false)
    private String imageName;

}
