package com.michaelszymczak.supermarket.domain;

public class Quantity {
    private final long howMany;

    public static Quantity items(long howMany) {
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

    @Override
    public String toString() {
        return "Quantity{" +
                "howMany=" + howMany +
                '}';
    }

    public Quantity reminder(Quantity dividend) {
        if (howMany == 0) {
            return dividend;
        } else {
            return Quantity.items(dividend.getHowMany() % howMany);
        }
    }
}
