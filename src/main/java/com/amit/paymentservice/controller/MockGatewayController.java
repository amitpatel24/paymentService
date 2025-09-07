package com.amit.paymentservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mock-gateway")
public class MockGatewayController {
    @PostMapping("/pay")
    public Map<String, String> pay(@RequestBody Map<String, Object> req) {
        return Map.of("status", "SUCCESS", "transactionId", UUID.randomUUID().toString());
    }
}

