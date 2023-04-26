package com.bbsw.bitboxer2.practica.enums;

public enum UserRoleEnum {
    USER("U"), ADMIN("A");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
