package com.bbsw.bitboxer2.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbsw.bitboxer2.practica.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
