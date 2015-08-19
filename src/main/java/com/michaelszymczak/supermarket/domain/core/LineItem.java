package com.michaelszymczak.supermarket.domain.core;

public class LineItem {
    private final Product product;
    private final Quantity quantity;
    private final Weight weight;
    private final Money priceForAllProducts;

    public static LineItem withQuantity(Product product, Quantity quantity, Money priceForAllProducts) {
        return new LineItem(product, quantity, Weight.grams(0), priceForAllProducts);
    }

    public static LineItem withWeight(Product product, Weight weight, Money priceForAllProducts) {
        return new LineItem(product, Quantity.items(0), weight, priceForAllProducts);
    }

    private LineItem(Product product, Quantity quantity, Weight weight, Money priceForAllProducts) {
        this.product = product;
        this.quantity = quantity;
        this.weight = weight;
        this.priceForAllProducts = priceForAllProducts;
    }

    public Money getPrice() {
        return priceForAllProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineItem lineItem = (LineItem) o;

        if (product != null ? !product.equals(lineItem.product) : lineItem.product != null) return false;
        if (quantity != null ? !quantity.equals(lineItem.quantity) : lineItem.quantity != null) return false;
        if (weight != null ? !weight.equals(lineItem.weight) : lineItem.weight != null) return false;
        return !(priceForAllProducts != null ? !priceForAllProducts.equals(lineItem.priceForAllProducts) : lineItem.priceForAllProducts != null);

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (priceForAllProducts != null ? priceForAllProducts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", weight=" + weight +
                ", priceForAllProducts=" + priceForAllProducts +
                '}';
    }
}
