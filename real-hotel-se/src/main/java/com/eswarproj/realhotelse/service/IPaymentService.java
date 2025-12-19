package com.eswarproj.realhotelse.service;

import com.eswarproj.realhotelse.model.Payment;

public interface IPaymentService {
    Payment processPayment(Long bookingId, Payment payment);

    Payment getPaymentByBookingId(Long bookingId);
}
