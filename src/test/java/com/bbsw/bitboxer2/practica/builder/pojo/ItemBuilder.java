package com.bbsw.bitboxer2.practica.builder.pojo;

import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.PriceReduction;
import com.bbsw.bitboxer2.practica.model.Supplier;
import com.bbsw.bitboxer2.practica.model.User;

import java.time.LocalDate;
import java.util.Set;

public class ItemBuilder {

    private Long itemCode;
    private String description;
    private double price;
    private ItemStateEnum itemState;
    private LocalDate creationDate;
    private User creator;
    private Set<PriceReduction> priceReductions;
    private Set<Supplier> suppliers;

    private ItemBuilder() {
        super();
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withItemCode(Long itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public ItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemBuilder withItemState(ItemStateEnum itemState) {
        this.itemState = itemState;
        return this;
    }

    public ItemBuilder withCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ItemBuilder withCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public ItemBuilder withPriceReductions(Set<PriceReduction> priceReductions) {
        this.priceReductions = priceReductions;
        return this;
    }

    public ItemBuilder withSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
        return this;
    }

    public Item build() {
        Item item = new Item();
        item.setItemCode(this.itemCode);
        item.setDescription(this.description);
        item.setPrice(this.price);
        item.setItemState(this.itemState);
        item.setCreationDate(this.creationDate);
        item.setCreator(this.creator);
        item.setPriceReductions(this.priceReductions);
        item.setSuppliers(this.suppliers);
        return item;
    }

}
