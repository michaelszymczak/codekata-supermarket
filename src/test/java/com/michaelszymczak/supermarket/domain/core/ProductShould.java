package com.michaelszymczak.supermarket.domain.core;

import com.michaelszymczak.supermarket.domain.core.IllegalCode;
import com.michaelszymczak.supermarket.domain.core.Product;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ProductShould {

    @Test
    public void be_comparable() throws Exception {
        assertThat(Product.ofCode("FOO"), is(Product.ofCode("FOO")));
        assertThat(Product.ofCode("FOO"), is(not(Product.ofCode("BAR"))));
    }

    @Test(expected = IllegalCode.class)
    public void not_allow_empty_codes() throws Exception {
        Product.ofCode("");
    }

    @Test(expected = IllegalCode.class)
    public void not_allow_null_codes() throws Exception {
        Product.ofCode("");
    }
}
