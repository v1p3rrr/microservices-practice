package com.vpr.servicealpha;

import com.vpr.servicealpha.models.TaxiOrder;
import com.vpr.servicealpha.service.TaxiOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServiceAlphaApplication.class)
public class SpringBootJPAIntegrationTest {

    @Autowired
    private TaxiOrderService taxiOrderService;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetrieveEntity_thenOK() {
        TaxiOrder initialTaxiOrder = new TaxiOrder("Start address", 1.0f, 342.34f, "Destination address, Some Street, building 31", 23.3456f, 34f, true);
        Long orderId = taxiOrderService
          .addTaxiOrder(initialTaxiOrder);
        TaxiOrder retrievedTaxiOrder = taxiOrderService
          .getTaxiOrder(orderId).orElse(null);
 
        assertNotNull(retrievedTaxiOrder, "Retrieved order is not null");
        assertEquals(orderId, retrievedTaxiOrder.getId(), "Comparison of ids");
        assertEquals(initialTaxiOrder.getDestinationAddress(), retrievedTaxiOrder.getDestinationAddress(), "Comparison of DestinationAddress");
        assertEquals(initialTaxiOrder.getDestinationLatitude(), retrievedTaxiOrder.getDestinationLatitude(), "Comparison of DestinationLatitude");
        assertEquals(initialTaxiOrder.getDestinationLongitude(), retrievedTaxiOrder.getDestinationLongitude(), "Comparison of DestinationLongitude");
        assertEquals(initialTaxiOrder.getStartAddress(), retrievedTaxiOrder.getStartAddress(), "Comparison of StartAddress");
        assertEquals(initialTaxiOrder.getStartLatitude(), retrievedTaxiOrder.getStartLatitude(), "Comparison of StartLatitude");
        assertEquals(initialTaxiOrder.getStartLongitude(), retrievedTaxiOrder.getStartLongitude(), "Comparison of StartLongitude");
    }
}