package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.core.Money;
import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.core.Quantity;

public class CurrentPriceAndRemainingQuantity {

    public final Product product;
    public final Money price;
    public final Quantity quantity;

    static CurrentPriceAndRemainingQuantity initiateForProductAndQuantity(Product product, Quantity quantity) {
        return new CurrentPriceAndRemainingQuantity(product, Money.NOTHING, quantity);
    }

    CurrentPriceAndRemainingQuantity(Product product, Money price, Quantity quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public boolean noItemLeft() {
        return quantity.isEmpty();
    }

    public Money currentPriceAdding(Money toAdd) {
        return price.adding(toAdd);
    }
}
