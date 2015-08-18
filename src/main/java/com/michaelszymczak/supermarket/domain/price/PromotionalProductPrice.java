package com.michaelszymczak.supermarket.domain.price;

import com.michaelszymczak.supermarket.domain.Money;
import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;

public class PromotionalProductPrice {
    private final Product product;

    private final Quantity referenceQuantity;
    private final Money referencePrice;
    public PromotionalProductPrice(Product product, Quantity referenceQuantity, Money referencePrice) {

        this.product = product;
        this.referenceQuantity = referenceQuantity;
        this.referencePrice = referencePrice;
    }

    public Money calculateFor(Product product, Quantity quantity) {
        if (! this.product.equals(product)) {
            throw new ProductAndPriceMismatch("Cannot calculate " + this.product + " price for " + product);
        }
        return referencePrice.times(quantity.getHowMany() / referenceQuantity.getHowMany());
    }

    public Product getProduct() {
        return product;
    }

    public Quantity notQualify(Quantity candidates) {
        return referenceQuantity.reminder(candidates);
    }
}