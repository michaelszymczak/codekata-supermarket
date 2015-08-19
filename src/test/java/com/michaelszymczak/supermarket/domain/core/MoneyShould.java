package com.michaelszymczak.supermarket.domain.core;

import com.michaelszymczak.supermarket.domain.core.IllegalAmoutOfMoney;
import com.michaelszymczak.supermarket.domain.core.Money;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MoneyShould {

    @Test
    public void be_comparable() throws Exception {
        assertThat(Money.pence(10), is(Money.pence(10)));
        assertThat(Money.pence(10), is(not(Money.pence(15))));
    }

    @Test
    public void can_be_nothing() throws Exception {
        assertThat(Money.NOTHING, is(Money.pence(0)));
    }

    @Test(expected = IllegalAmoutOfMoney.class)
    public void prevent_from_creating_invalid_money() throws Exception {
        Money.pence(-1);
    }

    @Test
    public void be_able_to_return_added_version() throws Exception {
        assertThat(Money.pence(10).adding(Money.pence(5)), is(Money.pence(15)));
    }

    @Test
    public void be_able_to_return_multiplied_version() throws Exception {
        assertThat(Money.pence(10).times(5), is(Money.pence(50)));
    }

    @Test
    public void be_able_to_return_divided_version() throws Exception {
        assertThat(Money.pence(10).times(0.5), is(Money.pence(5)));
    }

    @Test
    public void round_up() throws Exception {
        assertThat(Money.pence(10).times(0.01), is(Money.pence(1)));
    }

    @Test(expected = IllegalAmoutOfMoney.class)
    public void prevent_from_negating_value() throws Exception {
        Money.pence(10).times(-1);
    }

}