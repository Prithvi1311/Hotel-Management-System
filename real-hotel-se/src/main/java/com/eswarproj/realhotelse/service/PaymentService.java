package com.eswarproj.realhotelse.service;

import com.eswarproj.realhotelse.exception.ResourceNotFoundException;
import com.eswarproj.realhotelse.model.BookedRoom;
import com.eswarproj.realhotelse.model.Payment;
import com.eswarproj.realhotelse.repository.BookingRepository;
import com.eswarproj.realhotelse.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Payment processPayment(Long bookingId, Payment payment) {
        BookedRoom booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for ID: " + bookingId));

        if (payment.getAmount() == null) {
            throw new IllegalArgumentException("Payment amount is required");
        }

        payment.setBooking(booking);
        payment.setPaymentStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());

        // Update booking status
        booking.setBookingStatus("CONFIRMED");
        bookingRepository.save(booking);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByBookingId(Long bookingId) {
        return paymentRepository.findByBooking_BookingId(bookingId);
    }
}
