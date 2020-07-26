package ods.raidplanner.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ods.raidplanner.enums.CharacterRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class CharacterRole extends ODSEntity {

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private CharacterRoleEnum roleName;
}
