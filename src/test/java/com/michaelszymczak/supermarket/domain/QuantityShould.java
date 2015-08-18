package com.michaelszymczak.supermarket.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class QuantityShould {

    @Test
    public void create_value_object_with_quantity() throws Exception {
        Assert.assertEquals(Quantity.of(10).getHowMany(), 10);
    }

    @Test
    public void be_comparable() throws Exception {
        assertThat(Quantity.of(1), is(Quantity.of(1)));
        assertThat(Quantity.of(1), is(not(Quantity.of(2))));
    }

    @Test(expected = IllegalQuantity.class)
    public void prevent_from_creating_incorrect_quantity() throws Exception {
        Quantity.of(-1);
    }

    @Test
    public void calculate_reminder() throws Exception {
        assertThat(Quantity.of(5).reminder(Quantity.of(4)), is(Quantity.of(4)));
        assertThat(Quantity.of(5).reminder(Quantity.of(6)), is(Quantity.of(1)));
        assertThat(Quantity.of(0).reminder(Quantity.of(6)), is(Quantity.of(6)));
        assertThat(Quantity.of(0).reminder(Quantity.of(0)), is(Quantity.of(0)));
    }
}
