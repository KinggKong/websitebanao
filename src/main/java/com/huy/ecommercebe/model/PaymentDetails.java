package com.huy.ecommercebe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PaymentDetails {
    String paymentMethod;
    String paymentStatus;
    String paymentId;
    String razopayPaymentLinkId;
    String razopayPaymentLinkReferenceId;
    String razopayPaymentLinkStatus;
    String razopayPaymentId;
}
