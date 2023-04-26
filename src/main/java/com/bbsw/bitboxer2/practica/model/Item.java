package com.bbsw.bitboxer2.practica.model;

import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="item")
@NoArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1, schema = "erp")
    @Column(name = "itemcode")
    private Long itemCode;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "state", nullable = false)
    private ItemStateEnum state;

    @Column(name = "creationDate", nullable = false, updatable = false)
    @CreatedDate
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User creator;

    //private List<PriceReduction> priceReductions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "item_supplier",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"),
        schema = "erp"
    )
    private Set<Supplier> suppliers;

    public Item(Long itemCode, String description, double price, ItemStateEnum state, LocalDate creationDate,
            User creator) {
        this.itemCode = itemCode;
        this.description = description;
        this.price = price;
        this.state = state;
        this.creationDate = creationDate;
        this.creator = creator;
        //this.priceReductions = new ArrayList<>();
        this.suppliers = new HashSet<>();
    }

}
