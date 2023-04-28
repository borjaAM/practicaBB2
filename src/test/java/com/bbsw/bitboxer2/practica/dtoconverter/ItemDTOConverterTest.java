package com.bbsw.bitboxer2.practica.dtoconverter;

import com.bbsw.bitboxer2.practica.PracticaApplication;
import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.dto.converters.ItemDTOConverter;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.PriceReduction;
import com.bbsw.bitboxer2.practica.model.Supplier;
import com.bbsw.bitboxer2.practica.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PracticaApplication.class)
class ItemDTOConverterTest {

    private ItemDTOConverter itemDTOConverter;

    private Item item1;
    private Item item2;

    private ItemDTO itemDTO1;
    private ItemDTO itemDTO2;

    @BeforeEach
    public void setup() {
        itemDTOConverter = new ItemDTOConverter();

        item1 = Builders.firstItem();
        item2 = Builders.secondItem();

        itemDTO1 = Builders.firstItemDTO();
        itemDTO2 = Builders.secondItemDTO();

        User user = Builders.firstUser();
        User user2 = Builders.secondUser();

        UserDTO userDTO1 = Builders.firstUserDTO();
        UserDTO userDTO2 = Builders.secondUserDTO();

        PriceReduction priceReduction = Builders.secondPriceReduction();

        PriceReductionDTO priceReductionDTO = Builders.secondPriceReductionDTO();

        Supplier supplier = Builders.firstSupplier();
        supplier.setItems(Set.of(item1, item2));

        SupplierDTO supplierDTO = Builders.firstSupplierDTO();
        supplierDTO.setItems(Set.of(itemDTO1, itemDTO2));

        user.setItems(List.of(item1));
        user2.setItems(List.of(item2));

        userDTO1.setItems(List.of(itemDTO1));
        userDTO2.setItems(List.of(itemDTO2));

        item1.setCreator(user);
        item2.setCreator(user2);

        itemDTO1.setCreator(userDTO1);
        itemDTO2.setCreator(userDTO2);

        item2.setPriceReductions(Set.of(priceReduction));
        item2.setSuppliers(Set.of(supplier));

        itemDTO2.setPriceReductions(Set.of(priceReductionDTO));
        itemDTO2.setSuppliers(Set.of(supplierDTO));
    }

    @Test
    void convertToDTO(){
        assertEquals(itemDTO1.toString(), itemDTOConverter.convertToDTO(item1).toString());
        assertEquals(itemDTO2.toString(), itemDTOConverter.convertToDTO(item2).toString());
    }

    @Test
    void convertFromDTO(){
        assertEquals(item1.toString(), itemDTOConverter.convertFromDTO(itemDTO1).toString());
        assertEquals(item2.toString(), itemDTOConverter.convertFromDTO(itemDTO2).toString());
    }

}
