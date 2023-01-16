package com.vpr.servicebeta.controller;

import com.vpr.servicebeta.config.RabbitMQConfig;
import com.vpr.servicebeta.models.PaymentInfo;
import com.vpr.servicebeta.service.PaymentService;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class PaymentController {

    public Tracer tracer = jaegerTracer();

    @Autowired
    private RabbitTemplate rabbit;

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
        Span span = tracer.buildSpan("GET /healthcheck").start();
        System.out.println("healthy");
        span.finish();
        return "healthy";
    }

    @PostMapping("/addpayment")
    public Long addPayment(@RequestBody PaymentInfo paymentInfo){
        Span span = tracer.buildSpan("POST /addpayment").start();
        Long result = paymentService.addPayment(paymentInfo);
        span.finish();
        rabbit.convertAndSend(RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY, result);
        System.out.println("Sent payment ID: "+result);
        return result;
    }

}
