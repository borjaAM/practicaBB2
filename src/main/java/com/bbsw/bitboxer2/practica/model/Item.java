package com.bbsw.bitboxer2.practica.model;

import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="item")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "itemCode", scope = Item.class)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    @Column(name = "itemcode")
    private Long itemCode;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "state", nullable = false)
    private ItemStateEnum itemState;

    @Column(name = "creationDate", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User creator;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PriceReduction> priceReductions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "item_supplier",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Supplier> suppliers;

    public Item(Long itemCode, String description, double price, ItemStateEnum itemState, LocalDate creationDate,
                User creator) {
        this.itemCode = itemCode;
        this.description = description;
        this.price = price;
        this.itemState = itemState;
        this.creationDate = creationDate;
        this.creator = creator;
        this.priceReductions = new HashSet<>();
        this.suppliers = new HashSet<>();
    }

    @Override
    public String toString() {
        List<String> priceReductionIds = priceReductions.stream()
            .map(PriceReduction::getId)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.toList());
        List<String> supplierIds = suppliers.stream()
            .map(Supplier::getId)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.toList());
        return "Item{" +
                "itemCode=" + itemCode +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemState=" + itemState +
                ", creationDate=" + creationDate +
                ", creator=" + creator +
                ", priceReductions=" + priceReductionIds +
                ", suppliers=" + supplierIds +
                '}';
    }
}
