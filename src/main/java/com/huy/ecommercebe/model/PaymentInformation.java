package com.huy.ecommercebe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentInformation {
    @Column(name = "cardholder_name")
    String cardholderName;
    @Column(name = "card_number")
    String cardNumber;
    @Column(name = "expiaration_date")
    LocalDateTime expiarationDate;

    @Column(name = "cvv")
    String cvv;
}
