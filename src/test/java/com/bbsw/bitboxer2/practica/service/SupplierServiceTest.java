package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.PracticaApplication;
import com.bbsw.bitboxer2.practica.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PracticaApplication.class)
class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    private SupplierService supplierService;

    @BeforeEach
    void setUp() {
        supplierService = new SupplierService(supplierRepository);
    }

    @Test
    void findById() {
    }

    @Test
    void createSupplier() {
    }

    @Test
    void findSuppliersWithPriceReductions() {
    }
}