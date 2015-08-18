package com.michaelszymczak.supermarket.domain.price;

public class ProductAndPriceMismatch extends RuntimeException {
    public ProductAndPriceMismatch(String message) {
        super(message);
    }
}
