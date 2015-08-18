package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Quantity;

public class Money {
    private final long pence;

    public static Money ofPence(long pence) {
        return new Money(pence);
    }

    private Money(long pence) {
        if (pence < 0) {
            throw new IllegalAmoutOfMoney();
        }
        this.pence = pence;
    }

    public Money times(long multiplier) {
        return Money.ofPence(pence * multiplier);
    }

    public Money times(double multiplier) {
        return Money.ofPence((long) Math.ceil((double) pence * multiplier));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        return pence == money.pence;

    }

    @Override
    public int hashCode() {
        return (int) (pence ^ (pence >>> 32));
    }

    @Override
    public String toString() {
        return "Money{" +
                "pence=" + pence +
                '}';
    }
}
