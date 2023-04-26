package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.model.User;
import com.bbsw.bitboxer2.practica.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public User findById(Long id) {
        logger.info("Finding user: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public Long createUser(User user) {
        User userSaved = userRepository.save(user);
        logger.info("User saved: {}", userSaved);
        return userSaved.getId();
    }

    public User updateUser(User user) {
        return user;
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user: {}", id);
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setUserRoleEnum(user.getUserRoleEnum());
        return userDTO;
    }

    private User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setUserRoleEnum(userDTO.getUserRoleEnum());
        return user;
    }

}
