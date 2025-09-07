package com.amit.paymentservice.controller;

import com.amit.paymentservice.Model.PaymentRequest;
import com.amit.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String makePayment(@RequestBody PaymentRequest request) {
        log.info("Received payment request: type={}, amount={}", request.getPaymentType(), request.getAmount());

        return paymentService.processPayment(request);
    }
}
