package com.michaelszymczak.supermarket.domain.price;

import com.michaelszymczak.supermarket.domain.core.Money;
import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.core.Weight;

import org.junit.Assert;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.core.Money.pence;
import static org.hamcrest.CoreMatchers.is;

public class WeightedProductPriceShould {

    @Test
    public void calculate_price_for_product() throws Exception {
    //        Given
        Product carrots = new Product("CARROT");
        WeightedProductPrice carrotPrice = new WeightedProductPrice(carrots, Weight.grams(100), Money.pence(10));
    //        Then
        Assert.assertThat(carrotPrice.calculateFor(carrots, Weight.grams(500)), is(pence(50)));
        Assert.assertThat(carrotPrice.calculateFor(carrots, Weight.grams(10)), is(pence(1)));
    }

    @Test(expected = ProductAndPriceMismatch.class)
    public void reject_price_calculation_for_different_product() throws Exception {
    //        Given
        Product carrots = new Product("CARROT");
        Product tomatoes = new Product("TOMATO");
        WeightedProductPrice carrotPrice = new WeightedProductPrice(carrots, Weight.grams(100), Money.pence(10));
    //        When
        carrotPrice.calculateFor(tomatoes, Weight.grams(500));
    }




}
