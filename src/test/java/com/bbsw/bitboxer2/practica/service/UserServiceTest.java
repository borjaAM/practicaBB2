package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.PracticaApplication;
import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.model.User;
import com.bbsw.bitboxer2.practica.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PracticaApplication.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user1;
    private User user2;

    private UserDTO userDTO1;
    private UserDTO userDTO2;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);

        user1 = Builders.firstUser();
        user2 = Builders.secondUser();

        userDTO1 = Builders.firstUserDTO();
        userDTO2 = Builders.secondUserDTO();
    }

    @Test
    void findAll() {
        assertEquals(List.of(), this.userRepository.findAll());
        when(this.userRepository.findAll()).thenReturn(List.of(user1, user2));
        assertEquals(List.of(userDTO1, userDTO2).toString(), this.userService.findAll().toString());
    }

    @Test
    void findById() {
        when(this.userRepository.findById(userDTO1.getId())).thenReturn(Optional.ofNullable(user1));
        assertEquals(userDTO1.toString(), this.userService.findById(userDTO1.getId()).toString());
        when(this.userRepository.findById(userDTO2.getId())).thenReturn(Optional.ofNullable(user2));
        assertEquals(userDTO2.toString(), this.userService.findById(userDTO2.getId()).toString());
        assertNull(this.userService.findById(3L));
    }

    @Test
    void createUser() {
        this.userService.createUser(userDTO1);
        verify(this.userRepository).save(user1);
    }

    @Test
    void deleteUser() {
        this.userService.deleteUser(userDTO1.getId());
        verify(this.userRepository).deleteById(userDTO1.getId());
    }

}