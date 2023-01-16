package com.vpr.servicebeta;

import com.vpr.servicebeta.models.PaymentInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentWithCommissionTest {

    @Test
    public void checkPriceWithCommissionCalculation() {
        PaymentInfo paymentInfo = new PaymentInfo(1L, 119.5f, "rub", "alpha", 15);

        assertEquals(137.425f, paymentInfo.getPriceWithCommission(), "Should return correct price with commission (119.5+15%)");
    }
}