package com.bbsw.bitboxer2.practica.dto;

import com.bbsw.bitboxer2.practica.enums.UserRoleEnum;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private UserRoleEnum userRole;

}
