package com.vpr.servicebeta.service;

import com.vpr.servicebeta.models.PaymentInfo;
import com.vpr.servicebeta.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import io.micrometer.core.annotation.Timed;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Timed(value = "ExtractSinglePaymentFromDB")
    public Optional<PaymentInfo> getPayment(Long id){
        return paymentRepository.findById(id);
    }

    @Timed("ExtractAllPaymentsFromDB")
    public List<PaymentInfo> getAllPayments(){
        return paymentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Timed("AddPaymentToDB")
    public Long addPayment(PaymentInfo paymentInfo){
        return paymentRepository.saveAndFlush(paymentInfo).getId();
    }
}
