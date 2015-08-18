package com.michaelszymczak.supermarket.domain;

public class Product {
    private final String code;

    public static Product ofCode(String code) {
        if (code == null || "".equals(code)) {
            throw new IllegalCode();
        }
        return new Product(code);
    }

    public Product(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return !(code != null ? !code.equals(product.code) : product.code != null);

    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
