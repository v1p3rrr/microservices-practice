package com.vpr.servicebeta.controller;

import com.vpr.servicebeta.models.PaymentInfo;
import com.vpr.servicebeta.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getpayment")
    public Optional<PaymentInfo> getOrderById(@RequestParam Long id){
        return paymentService.getTaxiOrder(id);
    }

    @PostMapping("/addpayment")
    public Long addOrder(@RequestBody PaymentInfo paymentInfo){
        return paymentService.addTaxiOrder(paymentInfo);
    }
}
