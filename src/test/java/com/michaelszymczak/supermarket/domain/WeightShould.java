package com.michaelszymczak.supermarket.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class WeightShould {

    @Test
    public void create_value_object_with_weight_in_grams() throws Exception {
        Assert.assertEquals(Weight.ofGrams(100).getGrams(), 100);
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

    @Test
    public void compare_itself_to_another_weight() throws Exception {
    //        Given
        Weight _100_grams = Weight.ofGrams(100);
        Weight _50_grams = Weight.ofGrams(50);
        Weight _30_grams = Weight.ofGrams(30);
    //        Then
        Assert.assertThat(_100_grams.in(_50_grams), is(closeTo(2.0, 0.001)));
        Assert.assertThat(_50_grams.in(_50_grams), is(1.0));
        Assert.assertThat(_50_grams.in(_100_grams), is(0.5));
        Assert.assertThat(_100_grams.in(_30_grams), is(closeTo(3.333, 0.001)));
    }
}
