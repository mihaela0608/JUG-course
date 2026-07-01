package com.example.demo.UnitTests;


public class OrderService {

    private static final int LOW_STOCK_THRESHOLD = 5;

    private final PaymentGateway paymentGateway;
    private final InventoryRepository inventoryRepository;
    private final NotificationService notificationService;

    public OrderService(PaymentGateway paymentGateway,
                        InventoryRepository inventoryRepository,
                        NotificationService notificationService) {
        this.paymentGateway = paymentGateway;
        this.inventoryRepository = inventoryRepository;
        this.notificationService = notificationService;
    }


    public String placeOrder(String customerId, String productId, int quantity) {
        Product product = inventoryRepository.findById(productId)
                .orElseThrow(() -> new OrderException("Product not found: " + productId));

        if (product.getStockQuantity() < quantity) {
            throw new OrderException("Insufficient stock for product: " + productId);
        }

        double totalPrice = product.getPrice() * quantity;
        PaymentResult paymentResult = paymentGateway.processPayment(customerId, totalPrice);

        if (!paymentResult.isSuccess()) {
            throw new OrderException("Payment failed for customer: " + customerId);
        }

        int newQuantity = product.getStockQuantity() - quantity;
        inventoryRepository.updateStock(productId, newQuantity);

        String orderId = "ORDER-" + paymentResult.getTransactionId();
        notificationService.sendOrderConfirmation(customerId, orderId);

        if (newQuantity < LOW_STOCK_THRESHOLD) {
            notificationService.sendLowStockAlert(productId);
        }

        return orderId;
    }


    public void cancelOrder(String transactionId, String productId, int quantity) {
        paymentGateway.refund(transactionId);

        Product product = inventoryRepository.findById(productId)
                .orElseThrow(() -> new OrderException("Product not found: " + productId));

        inventoryRepository.updateStock(productId, product.getStockQuantity() + quantity);
    }
}
