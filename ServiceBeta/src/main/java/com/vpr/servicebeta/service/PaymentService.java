package com.vpr.servicebeta.service;

import com.vpr.servicebeta.models.PaymentInfo;
import com.vpr.servicebeta.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Optional<PaymentInfo> getTaxiOrder(Long id){
        return paymentRepository.findById(id);
    }

    public Long addTaxiOrder(PaymentInfo paymentInfo){
        return paymentRepository.saveAndFlush(paymentInfo).getId();
    }
}
