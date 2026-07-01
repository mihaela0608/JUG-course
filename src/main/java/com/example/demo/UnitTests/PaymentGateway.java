package com.example.demo.UnitTests;


public interface PaymentGateway {
    PaymentResult processPayment(String customerId, double amount);
    void refund(String transactionId);
}
