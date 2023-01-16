package com.vpr.servicebeta.controller;

import com.vpr.servicebeta.config.RabbitMQConfig;
import com.vpr.servicebeta.service.PaymentService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final PaymentService paymentService;

    @Autowired
    public MessageListener(PaymentService taxiOrderService) {
        this.paymentService = taxiOrderService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RESPONSE)
    public void listener(JSONArray jsonPaymentStatus) {
        try {
            Long paymentId = jsonPaymentStatus.getJSONObject(0).getLong("payment_id");
            boolean paymentStatus = jsonPaymentStatus.getJSONObject(0).getBoolean("status");

            System.out.println("Payment with id " + paymentId + ", processing result: " + paymentStatus);
            if (!paymentStatus) {
                Thread.sleep(500);
                paymentService.deletePaymentById(paymentId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}