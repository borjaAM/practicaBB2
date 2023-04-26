package com.bbsw.bitboxer2.practica.enums;

public enum ItemStateEnum {
    ACTIVE("A"), DISCONTINUED("D");

    private final String state;

    ItemStateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
