package com.bbsw.bitboxer2.practica.dtoconverter;

import com.bbsw.bitboxer2.practica.PracticaApplication;
import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.dto.converters.UserDTOConverter;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PracticaApplication.class)
class UserDTOConverterTest {

    private UserDTOConverter userDTOConverter;

    private User user1;
    private User user2;

    private UserDTO userDTO1;
    private UserDTO userDTO2;

    @BeforeEach
    void setUp() {
        userDTOConverter = new UserDTOConverter();
        user1 = Builders.firstUser();
        user2 = Builders.secondUser();

        userDTO1 = Builders.firstUserDTO();
        userDTO2 = Builders.secondUserDTO();

        Item item1 = Builders.firstItem();
        Item item2 = Builders.secondItem();

        ItemDTO itemDTO1 = Builders.firstItemDTO();
        ItemDTO itemDTO2 = Builders.secondItemDTO();

        user2.setItems(List.of(item1, item2));
        userDTO2.setItems(List.of(itemDTO1, itemDTO2));
    }

    @Test
    void convertToDTO() {
        assertEquals(userDTO1.toString(), userDTOConverter.convertToDTO(user1).toString());
        assertEquals(userDTO2.toString(), userDTOConverter.convertToDTO(user2).toString());
    }

    @Test
    void convertFromDTO() {
        assertEquals(user1.toString(), userDTOConverter.convertFromDTO(userDTO1).toString());
        assertEquals(user2.toString(), userDTOConverter.convertFromDTO(userDTO2).toString());
    }

}
