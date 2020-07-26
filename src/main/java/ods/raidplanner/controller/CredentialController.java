package ods.raidplanner.controller;

import ods.raidplanner.dto.LoginDTO;
import ods.raidplanner.dto.UserDTO;
import ods.raidplanner.exceptions.GenericErrorException;
import ods.raidplanner.exceptions.IncorrectLoginException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @PostMapping(value = "/authenticate", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity authenticate(@RequestBody LoginDTO dto) {
        try {
            UserDTO result = credentialService.authenticate(dto.getUsername(), dto.getPassword());
            return ResponseEntity.ok(result);
        }
        catch (IncorrectLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity signUp(@RequestBody UserDTO dto) {
        try {
            UserDTO result = credentialService.register(dto);
            return ResponseEntity.ok(result);
        }
        catch (IncorrectLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
