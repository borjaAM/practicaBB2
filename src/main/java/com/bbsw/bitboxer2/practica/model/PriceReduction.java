package com.bbsw.bitboxer2.practica.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="pricereduction")
@Data
public class PriceReduction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_reduction_id_seq")
    @SequenceGenerator(name = "price_reduction_id_seq", sequenceName = "price_reduction_id_seq", allocationSize = 1, schema = "erp")
    @Column(name = "id")
    private Long id;

    @Column(name = "reducedprice", nullable = false)
    private double reducedPrice;

    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemcode")
    @JsonManagedReference
    private Item item;
}
