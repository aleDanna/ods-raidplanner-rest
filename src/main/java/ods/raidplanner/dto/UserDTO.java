package ods.raidplanner.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO extends ODSDTO {

    private String name;
    private String surname;
    private String esoUsername;
    private Integer rank;
    private CredentialDTO credential;
    private List<CharacterDTO> characters;

}
