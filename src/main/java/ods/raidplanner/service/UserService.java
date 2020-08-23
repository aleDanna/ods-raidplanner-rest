package ods.raidplanner.service;

import ods.raidplanner.dto.UserDTO;
import ods.raidplanner.enums.CredentialRoleEnum;
import ods.raidplanner.exceptions.GenericErrorException;
import ods.raidplanner.exceptions.NotFoundException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.mapper.DTOMapper;
import ods.raidplanner.persistence.model.Credential;
import ods.raidplanner.persistence.model.User;
import ods.raidplanner.persistence.repository.CredentialRepository;
import ods.raidplanner.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CredentialRepository credentialRepository;


    public UserDTO getUser(Long userId) throws NotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException(userId, User.class);
        }
        return dtoMapper.toDto(user);
    }

    public UserDTO getUser(String username) throws ODSException {
        Credential credential = credentialRepository.findByUsername(username);
        if (credential == null) {
            throw new NotFoundException(username, Credential.class);
        }
        try {
            return dtoMapper.toDto(userRepository.findByCredential(credential));
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public List<CredentialRoleEnum> getRoles() {
        return Arrays.stream(CredentialRoleEnum.values())
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(UserDTO userDTO) throws ODSException {
        try {
            User user = userRepository.save(dtoMapper.toEntity(userDTO));
            return dtoMapper.toDto(user);
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public UserDTO getUserByEsoUsername(String esoUsername) throws NotFoundException {
        User user = userRepository.findByEsoUsername(esoUsername);
        if (user == null) {
            throw new NotFoundException(esoUsername, User.class);
        }
        return dtoMapper.toDto(user);
    }
}
