package com.vpr.servicealpha.controller;

import org.json.*;
import com.vpr.servicealpha.config.RabbitMQConfig;
import com.vpr.servicealpha.models.TaxiOrder;
import com.vpr.servicealpha.service.TaxiOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class MessageListener {

    private final TaxiOrderService taxiOrderService;


    @Autowired
    private RabbitTemplate rabbit;

    @Autowired
    public MessageListener(TaxiOrderService taxiOrderService) {
        this.taxiOrderService = taxiOrderService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void listener(Long paymentId) {
        System.out.println("Got payment ID: " + paymentId);
        if (Objects.nonNull(paymentId)){
            try {
                Optional<TaxiOrder> updatedOrder = taxiOrderService.getTaxiOrder(paymentId);
                JSONArray jsonArray = new JSONArray();
                if (updatedOrder.isPresent()) {
                    updatedOrder.get().setPaid(true);
                    taxiOrderService.addTaxiOrder(updatedOrder.get());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("payment_id", paymentId);
                    jsonObject.put("status", true);
                    jsonArray.put(jsonObject);
                }
                else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("payment_id", paymentId);
                    jsonObject.put("status", false);
                    jsonArray.put(jsonObject);
                }
                rabbit.convertAndSend(RabbitMQConfig.EXCHANGE,
                        RabbitMQConfig.ROUTING_KEY_RESPONSE, jsonArray.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}