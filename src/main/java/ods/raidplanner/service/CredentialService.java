package ods.raidplanner.service;

import ods.raidplanner.dto.UserDTO;
import ods.raidplanner.exceptions.GenericErrorException;
import ods.raidplanner.exceptions.IncorrectLoginException;
import ods.raidplanner.exceptions.NotFoundException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.mapper.DTOMapper;
import ods.raidplanner.persistence.model.Credential;
import ods.raidplanner.persistence.model.User;
import ods.raidplanner.persistence.repository.CredentialRepository;
import ods.raidplanner.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CredentialRepository credentialRepository;

    public UserDTO authenticate(String username, String password) throws ODSException {

        Credential credential = credentialRepository.findByUsernameAndPassword(username, password);
        if (credential == null) {
            throw new IncorrectLoginException(username, password);
        }
        try {
            return dtoMapper.toDto(userRepository.findByCredential(credential));
        }
        catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }

    public UserDTO register(UserDTO dto) throws ODSException {
        try {
            User user = userRepository.save(dtoMapper.toEntity(dto));
            return dtoMapper.toDto(user);
        } catch (Exception e) {
            throw new ODSException(e.getMessage());
        }
    }
}
