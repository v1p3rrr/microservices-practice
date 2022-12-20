package com.vpr.servicealpha.controller;

import com.vpr.servicealpha.models.TaxiOrder;
import com.vpr.servicealpha.service.TaxiOrderService;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class TaxiOrderController {

    public Tracer tracer = jaegerTracer();

    @Bean
    public io.opentracing.Tracer jaegerTracer() {
        JaegerTracer.Builder builder = new JaegerTracer.Builder("ServiceAlpha");
        return builder.build();
    }

    private final TaxiOrderService taxiOrderService;

    @Autowired
    public TaxiOrderController(TaxiOrderService taxiOrderService) {
        this.taxiOrderService = taxiOrderService;
    }

    @GetMapping("/order")
    public Optional<TaxiOrder> getOrderById(@RequestParam Long id){
        Span span = tracer.buildSpan("GET /order").start();
        Optional<TaxiOrder> result = taxiOrderService.getTaxiOrder(id);
        span.finish();
        return result;
    }

    @GetMapping("/allorders")
    public List<TaxiOrder> getAllPayments(){
        Span span = tracer.buildSpan("GET /allorders").start();
        List<TaxiOrder> result = taxiOrderService.getAllTaxiOrders();
        span.finish();
        return result;
    }

    @PostMapping("/addorder")
    public Long addOrder(@RequestBody TaxiOrder taxiOrder){
        Span span = tracer.buildSpan("POST /addorder").start();
        Long result = taxiOrderService.addTaxiOrder(taxiOrder);
        span.finish();
        return result;
    }

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "healthy";
    }
}
