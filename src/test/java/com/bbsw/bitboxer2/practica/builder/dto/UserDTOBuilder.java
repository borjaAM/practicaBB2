package com.bbsw.bitboxer2.practica.builder.dto;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.enums.UserRoleEnum;

import java.util.List;

public class UserDTOBuilder {

    private Long id;
    private String username;
    private String password;
    private UserRoleEnum userRole;
    private List<ItemDTO> items;

    private UserDTOBuilder() {
        super();
    }

    public static UserDTOBuilder anUser() {
        return new UserDTOBuilder();
    }

    public UserDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTOBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTOBuilder withUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserDTOBuilder withItems(List<ItemDTO> items) {
        this.items = items;
        return this;
    }

    public UserDTO build() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setUsername(this.username);
        userDTO.setPassword(this.password);
        userDTO.setUserRole(this.userRole);
        userDTO.setItems(this.items);
        return userDTO;
    }

}
