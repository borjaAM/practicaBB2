package com.bbsw.bitboxer2.practica.builder.pojo;

import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.Supplier;

import java.util.Set;

public class SupplierBuilder {

    private Long id;
    private String name;
    private String country;
    private Set<Item> items;

    private SupplierBuilder() {
        super();
    }

    public static SupplierBuilder aSupplier() {
        return new SupplierBuilder();
    }

    public SupplierBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SupplierBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SupplierBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public SupplierBuilder withItems(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Supplier build() {
        Supplier supplier = new Supplier();
        supplier.setId(this.id);
        supplier.setName(this.name);
        supplier.setCountry(this.country);
        supplier.setItems(this.items);
        return supplier;
    }

}
