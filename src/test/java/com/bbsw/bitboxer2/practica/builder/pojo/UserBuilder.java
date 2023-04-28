package com.bbsw.bitboxer2.practica.builder.pojo;

import com.bbsw.bitboxer2.practica.enums.UserRoleEnum;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.User;

import java.util.List;

public class UserBuilder {

    private Long id;
    private String username;
    private String password;
    private UserRoleEnum userRole;
    private List<Item> items;

    private UserBuilder() {
        super();
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserBuilder withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setUserRole(this.userRole);
        user.setItems(this.items);
        return user;
    }

}
