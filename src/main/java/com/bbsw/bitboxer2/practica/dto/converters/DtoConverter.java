package com.bbsw.bitboxer2.practica.dto.converters;

public interface DtoConverter<P, T> {

    P convertFromDTO(T dto);

    T convertToDTO(P pojo);

}
