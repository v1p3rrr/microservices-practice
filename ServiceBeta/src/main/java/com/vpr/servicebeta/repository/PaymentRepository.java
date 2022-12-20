package com.vpr.servicebeta.repository;

import com.vpr.servicebeta.models.PaymentInfo;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentInfo, Long> {
    @Timed(value = "ExtractSinglePaymentFromDB")
    @NonNull Optional<PaymentInfo> findById(@NonNull Long id);

    @Timed("ExtractAllPaymentsFromDB")
    @NonNull List<PaymentInfo> findAll(@NonNull Sort sort);

    @Timed("AddPaymentToDB")
    @NonNull PaymentInfo saveAndFlush(@NonNull PaymentInfo paymentInfo);
}
