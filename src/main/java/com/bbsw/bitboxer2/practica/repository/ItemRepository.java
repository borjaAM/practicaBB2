package com.bbsw.bitboxer2.practica.repository;

import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bbsw.bitboxer2.practica.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT item FROM Item item WHERE item.itemState = :itemState")
    List<Item> findByState(@Param("itemState") ItemStateEnum itemState);

    @Query("SELECT item FROM Item item WHERE item.itemCode = :itemCode")
    Item findByItemCode(@Param("itemCode") Long itemCode);
}
