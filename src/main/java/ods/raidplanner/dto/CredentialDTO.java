package ods.raidplanner.dto;

import lombok.Data;
import ods.raidplanner.enums.CredentialRoleEnum;

@Data
public class CredentialDTO extends ODSDTO {

    private String password;
    private CredentialRoleEnum role;
    private String username;

}
