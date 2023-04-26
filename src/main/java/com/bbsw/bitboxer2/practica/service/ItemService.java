package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        logger.info("Finding item: {}", id);
        return itemRepository.findById(id).orElse(null);
    }

    public Long createItem(Item item) {
        Item itemSaved = itemRepository.save(item);
        logger.info("Item saved: {}", itemSaved);
        return itemSaved.getItemCode();
    }

    public Item updateItem(Item item) {
        return null;
    }

    public void deleteItem(Long id) {
        logger.info("Deleting item: {}", id);
        itemRepository.deleteById(id);
    }

}
