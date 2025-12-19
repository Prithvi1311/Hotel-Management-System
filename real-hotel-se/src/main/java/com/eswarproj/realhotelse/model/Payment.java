package com.eswarproj.realhotelse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private BigDecimal amount;
    private String paymentStatus;
    private String transactionId;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private BookedRoom booking;
}
