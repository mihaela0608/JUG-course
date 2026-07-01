package com.example.demo.UnitTests;


public interface NotificationService {
    void sendOrderConfirmation(String customerId, String orderId);
    void sendLowStockAlert(String productId);
}