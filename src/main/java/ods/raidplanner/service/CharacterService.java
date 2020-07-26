package ods.raidplanner.service;

import ods.raidplanner.dto.CharacterDTO;
import ods.raidplanner.dto.CharacterRoleDTO;
import ods.raidplanner.exceptions.NotFoundException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.mapper.DTOMapper;
import ods.raidplanner.persistence.model.Character;
import ods.raidplanner.persistence.model.User;
import ods.raidplanner.persistence.repository.CharacterRepository;
import ods.raidplanner.persistence.repository.CharacterRoleRepository;
import ods.raidplanner.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterRoleRepository characterRoleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CharacterRoleDTO> getRoles() throws ODSException {
        try {
            List<CharacterRoleDTO> result = new ArrayList<>();
            characterRoleRepository.findAll().forEach(characterRole ->
                    result.add(dtoMapper.toDto(characterRole)));
            return result;
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public CharacterDTO updateCharacter(CharacterDTO dto) throws ODSException {
        Character character = characterRepository.findById(dto.getId()).orElse(null);
        if (character == null) {
            throw new NotFoundException(dto.getId(), Character.class);
        }
        User user = userRepository.findById(dto.getUserId()).orElse(null);
        if (user == null) {
            throw new NotFoundException(dto.getUserId(), User.class);
        }
        try {
            character = dtoMapper.toEntity(dto);
            character.setUser(user);
            return dtoMapper.toDto(characterRepository.save(character));
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public void delete(Long characterId) throws ODSException {
        Character character = characterRepository.findById(characterId).orElse(null);
        if (character == null) {
            throw new NotFoundException(characterId, Character.class);
        }
        try {
            characterRepository.delete(character);
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public CharacterDTO saveCharacter(CharacterDTO dto) throws ODSException {
        User user = userRepository.findById(dto.getUserId()).orElse(null);
        if (user == null) {
            throw new NotFoundException(dto.getUserId(), User.class);
        }
        try {
            Character character = dtoMapper.toEntity(dto);
            character.setUser(user);
            character = characterRepository.save(character);
            return dtoMapper.toDto(character);
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }
}
