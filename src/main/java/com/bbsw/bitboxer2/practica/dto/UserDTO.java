package com.bbsw.bitboxer2.practica.dto;

import com.bbsw.bitboxer2.practica.enums.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id", scope = UserDTO.class)
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private UserRoleEnum userRole;
    private List<ItemDTO> items;
    private List<ItemDTO> itemsDeactivated;

    @Override
    public String toString() {
        List<String> itemCodes = items.stream()
            .map(ItemDTO::getItemCode)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.toList());
        List<String> itemDeactivatedCodes = itemsDeactivated.stream()
                .map(ItemDTO::getItemCode)
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.toList());
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userRole=" + userRole +
                ", items=" + itemCodes +
                ", itemsDeactivated=" + itemDeactivatedCodes +
                '}';
    }
}
