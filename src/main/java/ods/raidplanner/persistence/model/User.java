package ods.raidplanner.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends ODSEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "eso_username", unique = true, nullable = false)
    private String esoUsername;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials_ref")
    private Credential credential;

    @OneToMany(mappedBy = "user")
    private List<Character> characters;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", esoUsername='" + esoUsername + '\'' +
                ", rank=" + rank +
                ", credential=" + credential +
                '}';
    }
}
