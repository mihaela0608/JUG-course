package com.example.demo.UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private NotificationService notificationService;

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService(paymentGateway, inventoryRepository, notificationService);
    }

    @Test
    public void testPlaceOrder_happyPath_returnsOrderId() {
        Product product = new Product("P1", "Keyboard", 50.0, 10);
        when(inventoryRepository.findById("P1")).thenReturn(Optional.of(product));
        when(paymentGateway.processPayment("CUST1", 100.0))
                .thenReturn(new PaymentResult(true, "TXN123"));

        String orderId = orderService.placeOrder("CUST1", "P1", 2);

        assertEquals("ORDER-TXN123", orderId);
        verify(inventoryRepository).updateStock("P1", 8);
        verify(notificationService).sendOrderConfirmation("CUST1", "ORDER-TXN123");
    }

    @Test
    public void testPlaceOrder_productNotFound_throwsException() {
        when(inventoryRepository.findById("P1")).thenReturn(Optional.empty());

        OrderException exception = assertThrows(OrderException.class,
                () -> orderService.placeOrder("CUST1", "P1", 2));

        assertEquals("Product not found: P1", exception.getMessage());
        verify(paymentGateway, never()).processPayment(anyString(), anyDouble());
    }

    @Test
    public void testPlaceOrder_insufficientStock_throwsExceptionAndSkipsPayment() {
        Product product = new Product("P1", "Keyboard", 50.0, 1);
        when(inventoryRepository.findById("P1")).thenReturn(Optional.of(product));

        OrderException exception = assertThrows(OrderException.class,
                () -> orderService.placeOrder("CUST1", "P1", 5));

        assertEquals("Insufficient stock for product: P1", exception.getMessage());
        verify(paymentGateway, never()).processPayment(anyString(), anyDouble());
        verify(inventoryRepository, never()).updateStock(anyString(), anyInt());
    }

    @Test
    public void testPlaceOrder_paymentFails_throwsExceptionAndDoesNotUpdateStock() {
        Product product = new Product("P1", "Keyboard", 50.0, 10);
        when(inventoryRepository.findById("P1")).thenReturn(Optional.of(product));
        when(paymentGateway.processPayment("CUST1", 100.0))
                .thenReturn(new PaymentResult(false, null));

        OrderException exception = assertThrows(OrderException.class,
                () -> orderService.placeOrder("CUST1", "P1", 2));

        assertEquals("Payment failed for customer: CUST1", exception.getMessage());
        verify(inventoryRepository, never()).updateStock(anyString(), anyInt());
        verify(notificationService, never()).sendOrderConfirmation(anyString(), anyString());
    }

    @Test
    public void testPlaceOrder_lowStockAfterOrder_sendsLowStockAlert() {
        Product product = new Product("P1", "Keyboard", 50.0, 6);
        when(inventoryRepository.findById("P1")).thenReturn(Optional.of(product));
        when(paymentGateway.processPayment("CUST1", 100.0))
                .thenReturn(new PaymentResult(true, "TXN123"));

        orderService.placeOrder("CUST1", "P1", 2);

        verify(notificationService).sendLowStockAlert("P1");
    }

    @Test
    public void testPlaceOrder_stockRemainsHigh_doesNotSendLowStockAlert() {
        Product product = new Product("P1", "Keyboard", 50.0, 20);
        when(inventoryRepository.findById("P1")).thenReturn(Optional.of(product));
        when(paymentGateway.processPayment("CUST1", 100.0))
                .thenReturn(new PaymentResult(true, "TXN123"));

        orderService.placeOrder("CUST1", "P1", 2);

        verify(notificationService, never()).sendLowStockAlert(anyString());
    }

    @Test
    public void testPlaceOrder_capturesCorrectPaymentAmount() {
        Product product = new Product("P1", "Keyboard", 25.5, 10);
        when(inventoryRepository.findById("P1")).thenReturn(Optional.of(product));
        when(paymentGateway.processPayment(anyString(), anyDouble()))
                .thenReturn(new PaymentResult(true, "TXN123"));

        ArgumentCaptor<Double> amountCaptor = ArgumentCaptor.forClass(Double.class);

        orderService.placeOrder("CUST1", "P1", 3);

        verify(paymentGateway).processPayment(eq("CUST1"), amountCaptor.capture());
        assertEquals(76.5, amountCaptor.getValue());
    }

    @Test
    public void testCancelOrder_refundsAndRestoresStock() {
        Product product = new Product("P1", "Keyboard", 50.0, 5);
        when(inventoryRepository.findById("P1")).thenReturn(Optional.of(product));

        orderService.cancelOrder("TXN123", "P1", 3);

        verify(paymentGateway).refund("TXN123");
        verify(inventoryRepository).updateStock("P1", 8);
    }

    @Test
    public void testCancelOrder_productNotFound_throwsException() {
        when(inventoryRepository.findById("P1")).thenReturn(Optional.empty());

        OrderException exception = assertThrows(OrderException.class,
                () -> orderService.cancelOrder("TXN123", "P1", 3));

        assertEquals("Product not found: P1", exception.getMessage());
        verify(paymentGateway, times(1)).refund("TXN123");
    }
}
