package com.vpr.servicebeta;

import com.vpr.servicebeta.models.PaymentInfo;
import com.vpr.servicebeta.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServiceBetaApplication.class)
public class SpringBootJPAIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void givenEntityRepository_whenSaveAndRetrieveEntity_thenOK() {
        PaymentInfo initialPaymentInfo = new PaymentInfo(1L, 250.5f, "rub", "alpha", 15);
        System.out.println(initialPaymentInfo.getId());
        Long paymentId = paymentService
          .addPayment(initialPaymentInfo);
        PaymentInfo retrievedPaymentInfo = paymentService
          .getPayment(paymentId).orElse(null);
 
        assertNotNull(retrievedPaymentInfo, "Retrieved payment is not null");
        assertEquals(paymentId, retrievedPaymentInfo.getId(), "Comparison of ids");
        assertEquals(initialPaymentInfo.getCost(), retrievedPaymentInfo.getCost(), "Comparison of cost");
        assertEquals(initialPaymentInfo.getPaymentType(), retrievedPaymentInfo.getPaymentType(), "Comparison of payment types");
        assertEquals(initialPaymentInfo.getCurrency(), retrievedPaymentInfo.getCurrency(), "Comparison of currencies");
        assertEquals(initialPaymentInfo.getCommissionPercent(), retrievedPaymentInfo.getCommissionPercent(), "Comparison of commission percents");
    }
}