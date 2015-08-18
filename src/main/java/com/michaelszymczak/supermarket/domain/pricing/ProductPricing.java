package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;

import java.util.List;

public class ProductPricing {
    private final List<SingleProductPrice> singleProductPrices;

    public ProductPricing(List<SingleProductPrice> singleProductPrices) {

        this.singleProductPrices = singleProductPrices;
    }

    public LineItem lineItemOf(Product product, Quantity quantity) {
        return new LineItem(product, quantity, singleProductPrices.get(0).calculateFor(product, quantity));
    }
}
