package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Quantity;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MoneyShould {

    @Test
    public void be_comparable() throws Exception {
        assertThat(Money.ofPence(10), is(Money.ofPence(10)));
        assertThat(Money.ofPence(10), is(not(Money.ofPence(15))));
    }

    @Test(expected = IllegalAmoutOfMoney.class)
    public void prevent_from_creating_invalid_money() throws Exception {
        Money.ofPence(-1);
    }

    @Test
    public void be_able_to_return_multiplied_version() throws Exception {
        assertThat(Money.ofPence(10).times(Quantity.of(5)), is(Money.ofPence(50)));
    }

}