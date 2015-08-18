package com.michaelszymczak.supermarket.domain.pricing;

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
        assertThat(Money.ofPence(10).times(5), is(Money.ofPence(50)));
    }

    @Test
    public void be_able_to_return_divided_version() throws Exception {
        assertThat(Money.ofPence(10).times(0.5), is(Money.ofPence(5)));
    }

    @Test
    public void round_up() throws Exception {
        assertThat(Money.ofPence(10).times(0.01), is(Money.ofPence(1)));
    }

    @Test(expected = IllegalAmoutOfMoney.class)
    public void prevent_from_negating_value() throws Exception {
        Money.ofPence(10).times(-1);
    }

}