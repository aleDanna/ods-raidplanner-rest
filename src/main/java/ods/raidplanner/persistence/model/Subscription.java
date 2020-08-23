package ods.raidplanner.persistence.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "raid_subscriptions")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Subscription extends ODSEntity {

    @Column(name = "group_number", nullable = false)
    private Integer groupNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_ref")
    private Character character;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raid_ref")
    private Raid raid;

    @Override
    public String toString() {
        return "Subscription{" +
                "groupNumber=" + groupNumber +
                ", character=" + character.getId() +
                '}';
    }
}
