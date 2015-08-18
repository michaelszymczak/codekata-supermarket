package com.michaelszymczak.supermarket.domain.pricing;

public class ProductAndPriceMismatch extends RuntimeException {
    public ProductAndPriceMismatch(String message) {
        super(message);
    }
}
