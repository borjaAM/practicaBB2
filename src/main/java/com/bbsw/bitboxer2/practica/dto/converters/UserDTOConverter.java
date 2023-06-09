package com.bbsw.bitboxer2.practica.dto.converters;

import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class UserDTOConverter implements DtoConverter<User, UserDTO> {

    @SneakyThrows
    @Override
    public User convertFromDTO(UserDTO dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String dtoJson = objectMapper.writeValueAsString(dto);
        return objectMapper.readValue(dtoJson, User.class);
    }

    @SneakyThrows
    @Override
    public UserDTO convertToDTO(User pojo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String pojoJson = objectMapper.writeValueAsString(pojo);
        return objectMapper.readValue(pojoJson, UserDTO.class);
    }

}
