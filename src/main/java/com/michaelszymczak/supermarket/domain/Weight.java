package com.michaelszymczak.supermarket.domain;


public class Weight {
    private long grams;

    public static Weight grams(long grams) {
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


    @Override
    public String toString() {
        return "Weight{" +
                "grams=" + grams +
                '}';
    }

    public double in(Weight otherWeight) {
        return (double) this.getGrams() / otherWeight.getGrams();
    }
}

