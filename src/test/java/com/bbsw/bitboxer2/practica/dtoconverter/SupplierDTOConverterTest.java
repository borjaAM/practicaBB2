package com.bbsw.bitboxer2.practica.dtoconverter;

import com.bbsw.bitboxer2.practica.PracticaApplication;
import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.dto.converters.SupplierDTOConverter;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PracticaApplication.class)
class SupplierDTOConverterTest {

    private SupplierDTOConverter supplierDTOConverter;

    private Supplier supplier1;
    private Supplier supplier2;

    private SupplierDTO supplierDTO1;
    private SupplierDTO supplierDTO2;

    @BeforeEach
    void setUp() {
        supplierDTOConverter = new SupplierDTOConverter();
        supplier1 = Builders.firstSupplier();
        supplier2 = Builders.secondSupplier();

        supplierDTO1 = Builders.firstSupplierDTO();
        supplierDTO2 = Builders.secondSupplierDTO();

        Item item1 = Builders.firstItem();
        Item item2 = Builders.secondItem();

        supplier1.setItems(Set.of(item1));
        supplier2.setItems(Set.of(item1, item2));

        ItemDTO itemDTO1 = Builders.firstItemDTO();
        ItemDTO itemDTO2 = Builders.secondItemDTO();

        supplierDTO1.setItems(Set.of(itemDTO1));
        supplierDTO2.setItems(Set.of(itemDTO1, itemDTO2));
    }

    @Test
    void convertToDTO() {
        assertEquals(supplierDTO1.toString(), supplierDTOConverter.convertToDTO(supplier1).toString());
        assertEquals(supplierDTO2.toString(), supplierDTOConverter.convertToDTO(supplier2).toString());
    }

    @Test
    void convertFromDTO() {
        assertEquals(supplier1.toString(), supplierDTOConverter.convertFromDTO(supplierDTO1).toString());
        assertEquals(supplier2.toString(), supplierDTOConverter.convertFromDTO(supplierDTO2).toString());
    }

}