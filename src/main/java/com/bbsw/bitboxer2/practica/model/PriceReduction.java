package com.bbsw.bitboxer2.practica.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="pricereduction")
@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id", scope = PriceReduction.class)
public class PriceReduction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_reduction_id_seq")
    @SequenceGenerator(name = "price_reduction_id_seq", sequenceName = "price_reduction_id_seq", allocationSize = 1)
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
    private Item item;

    @Override
    public String toString() {
        return "PriceReduction{" +
                "id=" + id +
                ", reducedPrice=" + reducedPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", item=" + item.getItemCode() +
                '}';
    }
}
