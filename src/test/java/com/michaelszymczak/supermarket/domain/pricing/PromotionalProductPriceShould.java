package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;
import org.junit.Assert;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.pricing.Money.ofPence;
import static org.hamcrest.CoreMatchers.is;

public class PromotionalProductPriceShould {

    @Test
    public void calculate_price_for_multiple_products() throws Exception {
    //        Given
        Product snickers = new Product("SNICKERS");
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.of(5), ofPence(200));
    //        Then
        Assert.assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(5)), is(ofPence(200)));
        Assert.assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(10)), is(ofPence(400)));
    }

    @Test
    public void calculate_only_price_for_quantity_above_the_threshold() throws Exception {
    //        Given
        Product snickers = new Product("SNICKERS");
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.of(5), ofPence(200));
        //        Then
        Assert.assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(4)), is(ofPence(0)));
        Assert.assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(6)), is(ofPence(200)));
        Assert.assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(9)), is(ofPence(200)));
    }

    @Test(expected = ProductAndPriceMismatch.class)
    public void reject_price_calculation_for_different_product() throws Exception {
    //        Given
        Product snickers = new Product("SNICKERS");
        Product mars = new Product("MARS");
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.of(5), ofPence(200));
    //        When
        _5snickersPrice.calculateFor(mars, Quantity.of(5));
    }


}
