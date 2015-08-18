package com.michaelszymczak.supermarket.domain.price;

import com.michaelszymczak.supermarket.domain.Money;
import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Weight;

import org.junit.Assert;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.Money.pence;
import static org.hamcrest.CoreMatchers.is;

public class WeightedProductPriceShould {

    @Test
    public void calculate_price_for_product() throws Exception {
    //        Given
        Product carrots = new Product("CARROT");
        WeightedProductPrice carrotPrice = new WeightedProductPrice(carrots, Weight.ofGrams(100), Money.pence(10));
    //        Then
        Assert.assertThat(carrotPrice.calculateFor(carrots, Weight.ofGrams(500)), is(pence(50)));
        Assert.assertThat(carrotPrice.calculateFor(carrots, Weight.ofGrams(10)), is(pence(1)));
    }

    @Test(expected = ProductAndPriceMismatch.class)
    public void reject_price_calculation_for_different_product() throws Exception {
    //        Given
        Product carrots = new Product("CARROT");
        Product tomatoes = new Product("TOMATO");
        WeightedProductPrice carrotPrice = new WeightedProductPrice(carrots, Weight.ofGrams(100), Money.pence(10));
    //        When
        carrotPrice.calculateFor(tomatoes, Weight.ofGrams(500));
    }




}
