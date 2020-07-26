package ods.raidplanner.persistence.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ods.raidplanner.enums.CredentialRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
@Data
@NoArgsConstructor
public class Credential extends ODSEntity {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private CredentialRoleEnum role;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

}
