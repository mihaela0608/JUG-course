package com.example.demo.UnitTests;

import java.util.Optional;


public interface InventoryRepository {
    Optional<Product> findById(String productId);
    void updateStock(String productId, int newQuantity);
}