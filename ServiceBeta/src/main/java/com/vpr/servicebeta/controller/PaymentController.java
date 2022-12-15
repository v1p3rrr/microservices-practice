package com.vpr.servicebeta.controller;

import com.vpr.servicebeta.models.PaymentInfo;
import com.vpr.servicebeta.service.PaymentService;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {

    public Tracer tracer = jaegerTracer();

    @Bean
    public io.opentracing.Tracer jaegerTracer() {
        JaegerTracer.Builder builder = new JaegerTracer.Builder("ServiceBeta");
        return builder.build();
    }

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment")
    public Optional<PaymentInfo> getPaymentById(@RequestParam Long id){
        Span span = tracer.buildSpan("GET /payment").start();
        Optional<PaymentInfo> result = paymentService.getPayment(id);
        span.finish();
        return result;
    }

    @GetMapping("/allpayments")
    public List<PaymentInfo> getAllPayments(){
        Span span = tracer.buildSpan("GET /allpayments").start();
        List<PaymentInfo> result = paymentService.getAllPayments();
        span.finish();
        return result;
    }

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "healthy";
    }

    @PostMapping("/addpayment")
    public Long addOrder(@RequestBody PaymentInfo paymentInfo){
        Span span = tracer.buildSpan("POST /addpayment").start();
        Long result = paymentService.addPayment(paymentInfo);
        span.finish();
        return result;
    }

}
