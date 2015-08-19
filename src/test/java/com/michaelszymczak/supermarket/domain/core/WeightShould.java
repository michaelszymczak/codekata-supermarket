package com.michaelszymczak.supermarket.domain.core;

import com.michaelszymczak.supermarket.domain.core.IllegalWeight;
import com.michaelszymczak.supermarket.domain.core.Weight;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class WeightShould {

    @Test
    public void create_value_object_with_weight_in_grams() throws Exception {
        Assert.assertEquals(Weight.grams(100).getGrams(), 100);
    }

    @Test
    public void be_comparable() throws Exception {
        assertThat(Weight.grams(1), is(Weight.grams(1)));
        assertThat(Weight.grams(1), is(not(Weight.grams(2))));
    }

    @Test(expected = IllegalWeight.class)
    public void prevent_from_creating_incorrect_weight() throws Exception {
        Weight.grams(-1);
    }

    @Test
    public void compare_itself_to_another_weight() throws Exception {
    //        Given
        Weight _100_grams = Weight.grams(100);
        Weight _50_grams = Weight.grams(50);
        Weight _30_grams = Weight.grams(30);
    //        Then
        Assert.assertThat(_100_grams.in(_50_grams), is(closeTo(2.0, 0.001)));
        Assert.assertThat(_50_grams.in(_50_grams), is(1.0));
        Assert.assertThat(_50_grams.in(_100_grams), is(0.5));
        Assert.assertThat(_100_grams.in(_30_grams), is(closeTo(3.333, 0.001)));
    }
}
