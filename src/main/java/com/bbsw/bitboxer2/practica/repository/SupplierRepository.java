package com.bbsw.bitboxer2.practica.repository;

import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
/*
    @Query("SELECT * " +
        "FROM SUPPLIER SUP" +
        "WHERE ID IN (" +
            "SELECT DISTINCT SUPPLIER_ID " +
            "FROM ITEM_SUPPLIER ITEM_SUP" +
            "INNER JOIN (" +
                "SELECT P.ID AS PRICEREDUCTION_ID, IT.ID AS ITEM_ID, DESCRIPTION, STATE, PRICE, REDUCEDPRICE" +
                "FROM ITEM IT" +
                "INNER JOIN PRICEREDUCTION P" +
                "ON IT.ID = P.ITEMCODE" +
            ") AS TMP ON ITEM_SUP.ITEM_ID = TMP.ITEM_ID" +
        ")")*/
    @Query("SELECT DISTINCT supplier " +
        "FROM Supplier supplier " +
        "INNER JOIN supplier.items sup_items " +
        "INNER JOIN sup_items.priceReductions")
    List<Supplier> findSuppliersWithPriceReductions();
/*
    @Query(SELECT S.ID AS SUPPLIER_ID, IT.ID AS ITEM_ID
        FROM ITEM IT
        INNER JOIN SUPPLIER S
        INNER JOIN (
            SELECT SUPPLIER_ID, MIN(PRICE) AS MIN_PRICE
            FROM ITEM_SUPPLIER ITEM_SUP
            INNER JOIN ITEM IT
            ON ITEM_SUP.ITEM_ID = IT.ID
            GROUP BY SUPPLIER_ID
        ) AS TMP
        ON S.ID = TMP.SUPPLIER_ID
        WHERE PRICE =TMP.MIN_PRICE)
        */
    @Query("SELECT new map(supplier.id, item) " +
            "FROM Supplier supplier " +
            "INNER JOIN supplier.items item " +
            "WHERE item.price = (SELECT min(item.price) FROM Item item)")
    List<Map<Long, Item>> findCheapestItemPerSupplier();

}
