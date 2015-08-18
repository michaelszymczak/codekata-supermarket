package com.michaelszymczak.supermarket.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Weight {
    private long grams;

    public static Weight ofGrams(long grams) {
        return new Weight(grams);
    }

    private Weight(long grams) {
        if (grams < 0) {
            throw new IllegalWeight();
        }
        this.grams = grams;
    }

    public long getGrams() {
        return grams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weight weight = (Weight) o;

        return grams == weight.grams;

    }

    @Override
    public int hashCode() {
        return (int) (grams ^ (grams >>> 32));
    }
}

