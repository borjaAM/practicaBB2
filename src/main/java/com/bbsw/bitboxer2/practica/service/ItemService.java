package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.converters.ItemDTOConverter;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemDTOConverter itemDTOConverter = new ItemDTOConverter();

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
            .map(itemDTOConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    public ItemDTO findById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            logger.info("Can't find item with id: {}", id);
            return null;
        }
        logger.info("Item found: {}", item);
        return itemDTOConverter.convertToDTO(item);
    }

    public ItemDTO findByItemCode(Long itemCode) {
        Item item = itemRepository.findByItemCode(itemCode);
        if (item == null) {
            logger.info("Can't find item with itemCode: {}", itemCode);
            return null;
        }
        logger.info("Item found: {}", item);
        return itemDTOConverter.convertToDTO(item);
    }

    public List<ItemDTO> findByState(ItemStateEnum itemState) {
        List<Item> items = itemRepository.findByState(itemState);
        return items.stream()
            .map(itemDTOConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    public Long createItem(ItemDTO itemDTO) {
        if (itemRepository.findByItemCode(itemDTO.getItemCode()) != null) {
            logger.info("Item already exists with item code: {}", itemDTO.getItemCode());
            return null;
        }
        Item itemSaved = itemRepository.save(itemDTOConverter.convertFromDTO(itemDTO));
        logger.info("Item saved: {}", itemSaved);
        return itemSaved.getItemCode();
    }

    public ItemDTO updateItem(ItemDTO itemDTO) {
        Item item = itemRepository.findById(itemDTO.getId()).orElse(null);
        if (item == null) {
            logger.info("Can't find item with id: {}", itemDTO.getItemCode());
            return null;
        }
        if (!ItemStateEnum.ACTIVE.equals(item.getItemState())) {
            logger.info("Can't update item ({}) because is not active", itemDTO.getItemCode());
            return null;
        }
        Item updatedItem = itemDTOConverter.convertFromDTO(itemDTO);
        item.setDescription(updatedItem.getDescription());
        item.setPrice(updatedItem.getPrice());
        item.setItemState(updatedItem.getItemState());
        item.setCreationDate(updatedItem.getCreationDate());
        item.setCreator(updatedItem.getCreator());
        item.setPriceReductions(updatedItem.getPriceReductions());
        item.setSuppliers(updatedItem.getSuppliers());
        return itemDTOConverter.convertToDTO(itemRepository.save(item));
    }

    public void deleteItem(Long id) {
        logger.info("Deleting item: {}", id);
        itemRepository.deleteById(id);
    }

}
