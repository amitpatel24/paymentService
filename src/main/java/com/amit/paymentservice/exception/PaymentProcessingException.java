package com.amit.paymentservice.exception;

public class PaymentProcessingException extends RuntimeException{

    public PaymentProcessingException (String message, Throwable cause){
        super(message, cause);
    }
}
