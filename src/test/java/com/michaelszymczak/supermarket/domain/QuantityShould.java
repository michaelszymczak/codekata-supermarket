package com.michaelszymczak.supermarket.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class QuantityShould {

    @Test
    public void create_value_object_with_quantity() throws Exception {
        Assert.assertEquals(Quantity.items(10).getHowMany(), 10);
    }

    @Test
    public void be_comparable() throws Exception {
        assertThat(Quantity.items(1), is(Quantity.items(1)));
        assertThat(Quantity.items(1), is(not(Quantity.items(2))));
    }

    @Test(expected = IllegalQuantity.class)
    public void prevent_from_creating_incorrect_quantity() throws Exception {
        Quantity.items(-1);
    }

    @Test
    public void calculate_reminder() throws Exception {
        assertThat(Quantity.items(5).reminder(Quantity.items(4)), is(Quantity.items(4)));
        assertThat(Quantity.items(5).reminder(Quantity.items(6)), is(Quantity.items(1)));
        assertThat(Quantity.items(0).reminder(Quantity.items(6)), is(Quantity.items(6)));
        assertThat(Quantity.items(0).reminder(Quantity.items(0)), is(Quantity.items(0)));
    }
}
