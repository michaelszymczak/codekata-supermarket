package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;

public class SingleProductPrice {
    private final Product product;
    private final Money price;

    public SingleProductPrice(Product product, Money price) {
        if (product == null || price == null) {
            throw new IllegalArgumentException("Product and price must not be null");
        }
        this.product = product;
        this.price = price;
    }

    public Money calculateFor(Product product, Quantity quantity) {
        if (! this.product.equals(product)) {
            throw new ProductAndPriceMismatch("Cannot calculate " + this.product + " price for " + product);
        }
        return price.times(quantity.getHowMany());
    }
}
