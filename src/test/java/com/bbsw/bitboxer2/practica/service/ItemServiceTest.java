package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.PracticaApplication;
import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PracticaApplication.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    private Item item1;
    private Item item2;
    private Item item3;

    private List<Item> items;

    private ItemDTO itemDTO1;
    private ItemDTO itemDTO2;
    private ItemDTO itemDTO3;

    private List<ItemDTO> itemsDTO;

    @BeforeEach
    public void setup() {
        itemService = new ItemService(this.itemRepository);

        item1 = Builders.firstItem();
        item1.setCreator(Builders.firstUser());
        item1.setSuppliers(Set.of(Builders.firstSupplier()));

        item2 = Builders.secondItem();
        item2.setCreator(Builders.secondUser());
        item2.setSuppliers(Set.of(Builders.firstSupplier(), Builders.secondSupplier()));
        item2.setPriceReductions(Set.of(Builders.firstPriceReduction()));

        item3 = Builders.thirdItem();
        item3.setCreator(Builders.secondUser());
        item3.setSuppliers(Set.of(Builders.firstSupplier(), Builders.secondSupplier()));
        item3.setPriceReductions(Set.of(Builders.firstPriceReduction(), Builders.thirdPriceReduction()));

        items = List.of(item1, item2, item3);

        itemDTO1 = Builders.firstItemDTO();
        itemDTO1.setCreator(Builders.firstUserDTO());
        itemDTO1.setSuppliers(Set.of(Builders.firstSupplierDTO()));

        itemDTO2 = Builders.secondItemDTO();
        itemDTO2.setCreator(Builders.secondUserDTO());
        itemDTO2.setSuppliers(Set.of(Builders.firstSupplierDTO(), Builders.secondSupplierDTO()));
        itemDTO2.setPriceReductions(Set.of(Builders.firstPriceReductionDTO()));

        itemDTO3 = Builders.thirdItemDTO();
        itemDTO3.setCreator(Builders.secondUserDTO());
        itemDTO3.setSuppliers(Set.of(Builders.firstSupplierDTO(), Builders.secondSupplierDTO()));
        itemDTO3.setPriceReductions(Set.of(Builders.firstPriceReductionDTO(), Builders.thirdPriceReductionDTO()));

        itemsDTO = List.of(itemDTO1, itemDTO2, itemDTO3);
    }

    @Test
    void findAll() {
        assertEquals(List.of(), this.itemService.findAll());
        when(itemRepository.findAll()).thenReturn(items);
        assertEquals(itemsDTO.toString(), this.itemService.findAll().toString());
    }

    @Test
    void testFindById() {
        when(itemRepository.findById(item1.getId())).thenReturn(Optional.ofNullable(item1));
        assertEquals(itemDTO1.toString(), this.itemService.findById(itemDTO1.getId()).toString());
        when(itemRepository.findById(item2.getId())).thenReturn(Optional.ofNullable(item2));
        assertEquals(itemDTO2.toString(), this.itemService.findById(itemDTO2.getId()).toString());
        assertNull(this.itemService.findById(itemDTO3.getId()));
    }

    @Test
    void findByItemCode() {
        when(itemRepository.findByItemCode(itemDTO1.getItemCode())).thenReturn(item1);
        assertEquals(itemDTO1.toString(), this.itemService.findByItemCode(itemDTO1.getItemCode()).toString());
        when(itemRepository.findByItemCode(itemDTO2.getItemCode())).thenReturn(item2);
        assertEquals(itemDTO2.toString(), this.itemService.findByItemCode(itemDTO2.getItemCode()).toString());
        assertNull(this.itemService.findByItemCode(itemDTO3.getItemCode()));
    }

    @Test
    void findByState() {
        assertEquals(List.of(), this.itemService.findByState(ItemStateEnum.ACTIVE));
        assertEquals(List.of(), this.itemService.findByState(ItemStateEnum.DISCONTINUED));
        when(itemRepository.findByState(ItemStateEnum.ACTIVE)).thenReturn(List.of(item1, item3));
        assertEquals(List.of(itemDTO1, itemDTO3).toString(), this.itemService.findByState(ItemStateEnum.ACTIVE).toString());
        when(itemRepository.findByState(ItemStateEnum.DISCONTINUED)).thenReturn(List.of(item2));
        assertEquals(List.of(itemDTO2).toString(), this.itemService.findByState(ItemStateEnum.DISCONTINUED).toString());
    }

    @Test
    void createItem() {
        when(itemRepository.save(item1)).thenReturn(item1);
        this.itemService.createItem(itemDTO1);
        verify(this.itemRepository).save(item1);
    }

    @Test
    void updateItem() {
        when(itemRepository.findById(itemDTO3.getId())).thenReturn(Optional.ofNullable(item3));
        assertNull(itemService.updateItem(itemDTO3));
        when(itemRepository.findById(itemDTO2.getId())).thenReturn(Optional.ofNullable(item2));
        assertNull(itemService.updateItem(itemDTO2));
        when(itemRepository.findById(itemDTO1.getId())).thenReturn(Optional.ofNullable(item1));
        ItemDTO itemDTOUpdated = Builders.firstItemDTO();
        itemDTOUpdated.setDescription("Description updated");
        itemDTOUpdated.setCreator(Builders.secondUserDTO());
        itemDTOUpdated.setSuppliers(Set.of(Builders.firstSupplierDTO()));
        Item updatedItem = Builders.firstItem();
        updatedItem.setDescription("Description updated");
        updatedItem.setCreator(Builders.secondUser());
        updatedItem.setSuppliers(Set.of(Builders.firstSupplier()));
        itemService.updateItem(itemDTOUpdated);
        verify(itemRepository).save(updatedItem);
    }

    @Test
    void deactivateItem() {
    }

    @Test
    void addPriceReduction() {
    }

    @Test
    void associateNewSupplier() {
    }

    @Test
    void deleteItem() {
        this.itemService.deleteItem(itemDTO1.getId());
        verify(this.itemRepository).deleteById(itemDTO1.getId());
    }

}
