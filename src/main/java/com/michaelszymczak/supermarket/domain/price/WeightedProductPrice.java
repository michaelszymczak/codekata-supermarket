package com.michaelszymczak.supermarket.domain.price;

import com.michaelszymczak.supermarket.domain.core.Money;
import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.core.Weight;

public class WeightedProductPrice {
    private final Product product;

    private final Weight referenceWeight;
    private final Money referencePrice;
    public WeightedProductPrice(Product product, Weight referenceWeight, Money referencePrice) {

        this.product = product;
        this.referenceWeight = referenceWeight;
        this.referencePrice = referencePrice;
    }

    public Money calculateFor(Product product, Weight weight) {
        if (! this.product.equals(product)) {
            throw new ProductAndPriceMismatch("Cannot calculate " + this.product + " price for " + product);
        }
        return referencePrice.times(weight.in(referenceWeight));
    }

    public Product getProduct() {
        return product;
    }
}