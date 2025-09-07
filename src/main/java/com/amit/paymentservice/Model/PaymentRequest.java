package com.amit.paymentservice.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {


    private PaymentType paymentType;
    private String cardNumber; // for card payments
    private String cardHolder; // for card payments
    private String expiry;     // for card payments
    private String cvv;        // for card payments
    private String upiId;      // for UPI payments
    private double amount;

}
