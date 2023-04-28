package com.bbsw.bitboxer2.practica.dtoconverter;

import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.dto.converters.PriceReductionDTOConverter;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.PriceReduction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceReductionDTOConverterTest {

    private PriceReductionDTOConverter priceReductionDTOConverter;

    private PriceReduction priceReduction1;
    private PriceReduction priceReduction2;

    private PriceReductionDTO priceReductionDTO1;
    private PriceReductionDTO priceReductionDTO2;

    @BeforeEach
    void setUp() {
        priceReductionDTOConverter = new PriceReductionDTOConverter();
        priceReduction1 = Builders.firstPriceReduction();
        priceReduction2 = Builders.secondPriceReduction();

        priceReductionDTO1 = Builders.firstPriceReductionDTO();
        priceReductionDTO2 = Builders.secondPriceReductionDTO();

        Item item1 = Builders.firstItem();
        Item item2 = Builders.secondItem();

        ItemDTO itemDTO1 = Builders.firstItemDTO();
        ItemDTO itemDTO2 = Builders.secondItemDTO();

        priceReduction1.setItem(item1);
        priceReduction2.setItem(item2);

        priceReductionDTO1.setItem(itemDTO1);
        priceReductionDTO2.setItem(itemDTO2);
    }

    @Test
    void convertToDTO() {
        assertEquals(priceReductionDTO1.toString(), priceReductionDTOConverter.convertToDTO(priceReduction1).toString());
        assertEquals(priceReductionDTO2.toString(), priceReductionDTOConverter.convertToDTO(priceReduction2).toString());
    }

    @Test
    void convertFromDTO() {
        assertEquals(priceReduction1.toString(), priceReductionDTOConverter.convertFromDTO(priceReductionDTO1).toString());
        assertEquals(priceReduction2.toString(), priceReductionDTOConverter.convertFromDTO(priceReductionDTO2).toString());
    }

}
