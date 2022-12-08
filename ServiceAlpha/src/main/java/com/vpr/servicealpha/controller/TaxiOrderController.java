package com.vpr.servicealpha.controller;

import com.vpr.servicealpha.models.TaxiOrder;
import com.vpr.servicealpha.service.TaxiOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaxiOrderController {

    private final TaxiOrderService taxiOrderService;

    @Autowired
    public TaxiOrderController(TaxiOrderService taxiOrderService) {
        this.taxiOrderService = taxiOrderService;
    }

    @GetMapping("/getorder")
    public Optional<TaxiOrder> getOrderById(@RequestParam Long id){
        return taxiOrderService.getTaxiOrder(id);
    }

    @PostMapping("/addorder")
    public Long addOrder(@RequestBody TaxiOrder taxiOrder){
        return taxiOrderService.addTaxiOrder(taxiOrder);
    }
}
