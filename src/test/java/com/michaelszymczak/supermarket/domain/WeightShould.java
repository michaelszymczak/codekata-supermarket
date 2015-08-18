package com.michaelszymczak.supermarket.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class WeightShould {

    @Test
    public void create_value_object_with_weight_in_grams() throws Exception {
    //        Given
        Weight weight = Weight.ofGrams(100);
    //        When
        long weightInGrams = weight.getGrams();
    //        Then
        Assert.assertEquals(weightInGrams, 100);
    }

    @Test
    public void be_comparable() throws Exception {
        assertThat(Weight.ofGrams(1), is(Weight.ofGrams(1)));
        assertThat(Weight.ofGrams(1), is(not(Weight.ofGrams(2))));
    }

    @Test(expected = IllegalWeight.class)
    public void prevent_from_creating_incorrect_weight() throws Exception {
        Weight.ofGrams(-1);
    }
}
