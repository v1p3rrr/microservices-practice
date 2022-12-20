package com.vpr.servicealpha.controller;

import com.vpr.servicealpha.config.MQConfig;
import com.vpr.servicealpha.models.TaxiOrder;
import com.vpr.servicealpha.service.TaxiOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MessageListener {

    private final TaxiOrderService taxiOrderService;

    @Autowired
    public MessageListener(TaxiOrderService taxiOrderService) {
        this.taxiOrderService = taxiOrderService;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(String message) {
        System.out.println(message);
        if (Objects.equals(message, "added new payment")){
            try {
                TaxiOrder updatedOrder = taxiOrderService.getLastTaxiOrder();
                updatedOrder.setPaid(true);
                taxiOrderService.addTaxiOrder(updatedOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}