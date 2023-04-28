package com.bbsw.bitboxer2.practica.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "supplier")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id", scope = Supplier.class)
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_id_seq")
    @SequenceGenerator(name = "supplier_id_seq", sequenceName = "supplier_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToMany(mappedBy = "suppliers")
    private Set<Item> items;

    public Supplier(String name, String country) {
        this.name = name;
        this.country = country;
        this.items = new HashSet<>();
    }

    @Override
    public String toString() {
        List<String> itemIds = items.stream()
            .map(Item::getItemCode)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.toList());
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", items=" + itemIds +
                '}';
    }
}
