package ods.raidplanner.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "raids")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Raid extends ODSEntity {

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_ref")
    private RaidGroup raidGroup;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "raid", cascade = CascadeType.REMOVE)
    private List<Subscription> subscriptions;

    @Override
    public String toString() {
        return "Raid{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", raidGroup=" + raidGroup +
                '}';
    }
}
