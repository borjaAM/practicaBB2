package com.bbsw.bitboxer2.practica.model;

import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    property = "id", scope = Item.class)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "itemcode", nullable = false, updatable = false, unique = true)
    private Long itemCode;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "state", nullable = false)
    private ItemStateEnum itemState;

    @Column(name = "creationDate", nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PriceReduction> priceReductions = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "item_supplier",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Supplier> suppliers = new HashSet<>();

    @Column(name = "deactivationReason")
    private String deactivationReason;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deactivationUser_id", referencedColumnName = "id")
    private User deactivationUser;

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
                ", deactivationReason=" + deactivationReason +
                ", deactivationUser=" + deactivationUser +
                ", priceReductions=" + priceReductionIds +
                ", suppliers=" + supplierIds +
                '}';
    }
}
