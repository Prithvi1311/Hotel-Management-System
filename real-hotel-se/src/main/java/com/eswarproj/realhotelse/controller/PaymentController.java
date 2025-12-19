package com.eswarproj.realhotelse.controller;

import com.eswarproj.realhotelse.model.Payment;
import com.eswarproj.realhotelse.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService paymentService;

    @PostMapping("/pay/{bookingId}")
    public ResponseEntity<Payment> processPayment(@PathVariable Long bookingId, @RequestBody Payment payment) {
        Payment processedPayment = paymentService.processPayment(bookingId, payment);
        return ResponseEntity.ok(processedPayment);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable Long bookingId) {
        Payment payment = paymentService.getPaymentByBookingId(bookingId);
        return ResponseEntity.ok(payment);
    }
}
