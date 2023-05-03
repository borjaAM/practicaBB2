package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.dto.converters.UserDTOConverter;
import com.bbsw.bitboxer2.practica.model.User;
import com.bbsw.bitboxer2.practica.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserDTOConverter userDTOConverter = new UserDTOConverter();

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(userDTOConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            logger.info("Can't find user with id: {}", id);
            return null;
        }
        logger.info("User found: {}", user);
        return userDTOConverter.convertToDTO(user);
    }

    public Long createUser(UserDTO userDTO) {
        User userSaved = userRepository.save(
            userDTOConverter.convertFromDTO(userDTO));
        logger.info("User saved: {}", userSaved);
        return userSaved.getId();
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user: {}", id);
        userRepository.deleteById(id);
    }

}
