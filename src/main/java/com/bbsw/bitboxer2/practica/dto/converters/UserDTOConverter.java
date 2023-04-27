package com.bbsw.bitboxer2.practica.dto.converters;

import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.model.User;

public class UserDTOConverter implements  DtoConverter<User, UserDTO> {

    @Override
    public User convertFromDTO(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setUserRole(dto.getUserRole());
        return user;
    }

    @Override
    public UserDTO convertToDTO(User pojo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(pojo.getId());
        userDTO.setUsername(pojo.getUsername());
        userDTO.setUserRole(pojo.getUserRole());
        return userDTO;
    }
}
