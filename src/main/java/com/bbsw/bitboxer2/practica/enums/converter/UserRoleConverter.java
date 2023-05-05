package com.bbsw.bitboxer2.practica.enums.converter;

import com.bbsw.bitboxer2.practica.enums.UserRoleEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRoleEnum, String> {

    @Override
    public String convertToDatabaseColumn(UserRoleEnum userRoleEnum) {
        return userRoleEnum == null ? null : userRoleEnum.toString();
    }

    @Override
    public UserRoleEnum convertToEntityAttribute(String role) {
        return Stream.of(UserRoleEnum.values())
            .filter(userRoleEnum -> role.equals(userRoleEnum.toString()))
            .findFirst()
            .orElseThrow(IllegalAccessError::new);
    }
}
