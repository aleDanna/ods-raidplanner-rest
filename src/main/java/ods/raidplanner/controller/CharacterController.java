package ods.raidplanner.controller;

import ods.raidplanner.dto.CharacterDTO;
import ods.raidplanner.dto.CharacterRoleDTO;
import ods.raidplanner.exceptions.NotFoundException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping(value = "/roles", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCharacterRoles() {
        try {
            List<CharacterRoleDTO> result = characterService.getRoles();
            return ResponseEntity.ok(result);
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateCharacter(@RequestBody CharacterDTO character) {
        try {
            CharacterDTO result = characterService.updateCharacter(character);
            return ResponseEntity.ok(result);
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{characterId}")
    public ResponseEntity deleteCharacter(@PathVariable Long characterId) {
        try {
            characterService.delete(characterId);
            return ResponseEntity.ok().build();
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity saveCharacter(@RequestBody CharacterDTO character) {
        try {
            CharacterDTO result = characterService.saveCharacter(character);
            return ResponseEntity.ok(result);
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
