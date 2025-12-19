package com.eswarproj.realhotelse.repository;

import com.eswarproj.realhotelse.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByBooking_BookingId(Long bookingId);
}
