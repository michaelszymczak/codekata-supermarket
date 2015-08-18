package com.michaelszymczak.supermarket.domain;

public class Quantity {
    private final long howMany;

    public static Quantity of(long howMany) {
        return new Quantity(howMany);
    }

    private Quantity(long howMany) {
        if (howMany < 0) {
            throw new IllegalQuantity();
        }
        this.howMany = howMany;
    }

    public long getHowMany() {
        return howMany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity quantity = (Quantity) o;

        return howMany == quantity.howMany;

    }

    @Override
    public int hashCode() {
        return (int) (howMany ^ (howMany >>> 32));
    }
}
