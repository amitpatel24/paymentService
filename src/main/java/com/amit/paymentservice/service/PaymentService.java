package com.amit.paymentservice.service;

import com.amit.paymentservice.Model.PaymentRequest;
import com.amit.paymentservice.Model.PaymentType;
import com.amit.paymentservice.exception.PaymentProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PaymentService {

    private final String PAYMENT_GATEWAY_URL = "http://localhost:8080/mock-gateway/pay";

    public String processPayment(PaymentRequest request) {
        log.info("Processing payment: type={}, amount={}", request.getPaymentType(), request.getAmount());

        RestTemplate restTemplate = new RestTemplate();

        Object payload;
        if (request.getPaymentType() == PaymentType.UPI) {
            payload = new UpiPayment(request.getUpiId(), request.getAmount());
        } else {
            payload = new CardPayment(
                    request.getCardNumber(),
                    request.getCardHolder(),
                    request.getExpiry(),
                    request.getCvv(),
                    request.getAmount()
            );
        }

        try {
            String response = restTemplate.postForObject(PAYMENT_GATEWAY_URL, payload, String.class);
            log.info("Payment response: {}", response);
            return response;
        } catch (RestClientException e) {
            log.error("Error while calling payment gateway", e);
            throw new PaymentProcessingException("Failed to process payment. Please try again later.", e);
        }
    }

    // Inner classes for demo payloads
    static class CardPayment {
        public String cardNumber, cardHolder, expiry, cvv;
        public double amount;
        public CardPayment(String cardNumber, String cardHolder, String expiry, String cvv, double amount) {
            this.cardNumber = cardNumber;
            this.cardHolder = cardHolder;
            this.expiry = expiry;
            this.cvv = cvv;
            this.amount = amount;
        }
    }

    static class UpiPayment {
        public String upiId;
        public double amount;
        public UpiPayment(String upiId, double amount) {
            this.upiId = upiId;
            this.amount = amount;
        }
    }
}
