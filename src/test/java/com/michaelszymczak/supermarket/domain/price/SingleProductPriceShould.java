package com.michaelszymczak.supermarket.domain.price;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;
import org.junit.Assert;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.Money.ofPence;
import static org.hamcrest.CoreMatchers.is;

public class SingleProductPriceShould {

    @Test
    public void calculate_price_for_product() throws Exception {
    //        Given
        Product snickers = new Product("SNICKERS");
        SingleProductPrice snickersPrice = new SingleProductPrice(snickers, ofPence(75));
    //        Then
        Assert.assertThat(snickersPrice.calculateFor(snickers, Quantity.of(1)), is(ofPence(75)));
        Assert.assertThat(snickersPrice.calculateFor(snickers, Quantity.of(2)), is(ofPence(150)));
        Assert.assertThat(snickersPrice.calculateFor(snickers, Quantity.of(0)), is(ofPence(0)));
    }

    @Test(expected = ProductAndPriceMismatch.class)
    public void reject_price_calculation_for_different_product() throws Exception {
    //        Given
        Product snickers = new Product("SNICKERS");
        Product mars = new Product("MARS");
        SingleProductPrice snickersPrice = new SingleProductPrice(snickers, ofPence(75));
    //        When
        snickersPrice.calculateFor(mars, Quantity.of(1));
    }


}
