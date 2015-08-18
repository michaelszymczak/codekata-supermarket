package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Money;
import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;

public class LineItem {
    private final Product product;
    private final Quantity quantity;
    private final Money priceForAllProducts;

    public LineItem(Product product, Quantity quantity, Money priceForAllProducts) {
        this.product = product;
        this.quantity = quantity;
        this.priceForAllProducts = priceForAllProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineItem lineItem = (LineItem) o;

        if (product != null ? !product.equals(lineItem.product) : lineItem.product != null) return false;
        if (quantity != null ? !quantity.equals(lineItem.quantity) : lineItem.quantity != null) return false;
        return !(priceForAllProducts != null ? !priceForAllProducts.equals(lineItem.priceForAllProducts) : lineItem.priceForAllProducts != null);

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (priceForAllProducts != null ? priceForAllProducts.hashCode() : 0);
        return result;
    }
}
