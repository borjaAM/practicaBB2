package com.bbsw.bitboxer2.practica.enums.converter;

import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ItemStateConverter implements AttributeConverter<ItemStateEnum, String> {

    @Override
    public String convertToDatabaseColumn(ItemStateEnum itemState) {
        return itemState == null ? null : itemState.getState();
    }

    @Override
    public ItemStateEnum convertToEntityAttribute(String state) {
        return Stream.of(ItemStateEnum.values())
                .filter(itemState -> state.equals(itemState.getState()))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
