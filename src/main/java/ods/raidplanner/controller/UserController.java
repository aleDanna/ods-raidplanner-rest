package ods.raidplanner.controller;

import ods.raidplanner.dto.UserDTO;
import ods.raidplanner.dto.ValueDTO;
import ods.raidplanner.enums.CredentialRoleEnum;
import ods.raidplanner.exceptions.NotFoundException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/esoUsername/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getEsoUsername(@PathVariable Long userId) {
        try {
            UserDTO result = userService.getUser(userId);
            return ResponseEntity.ok(ValueDTO.builder().value(result.getEsoUsername()).build());
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/findUser/{username}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable String username) {
        try {
            UserDTO result = userService.getUser(username);
            return ResponseEntity.ok(result);
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/roles", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getRoles() {
        List<CredentialRoleEnum> result = userService.getRoles();
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/updateUser", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO result = userService.updateUser(userDTO);
            return ResponseEntity.ok(result);
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/findByEsoUsername/{esoUsername}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity geByEsoUsername(@PathVariable String esoUsername) {
        try {
            UserDTO result = userService.getUserByEsoUsername(esoUsername);
            return ResponseEntity.ok(result);
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
