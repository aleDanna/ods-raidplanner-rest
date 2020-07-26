package ods.raidplanner.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
@Data
@NoArgsConstructor
public class Character extends ODSEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_ref")
    private CharacterRole role;

    @ManyToOne
    @JoinColumn(name = "user_ref")
    private User user;

    @OneToMany(mappedBy = "character", cascade = CascadeType.REMOVE)
    private List<Subscription> subscriptions;

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", role=" + role +
                ", user=" + user.getId() +
                '}';
    }
}
