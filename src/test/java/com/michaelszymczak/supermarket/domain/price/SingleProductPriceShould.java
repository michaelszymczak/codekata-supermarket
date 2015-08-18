package com.michaelszymczak.supermarket.domain.price;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;
import org.junit.Assert;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.Money.pence;
import static org.hamcrest.CoreMatchers.is;

public class SingleProductPriceShould {

    @Test
    public void calculate_price_for_product() throws Exception {
    //        Given
        Product snickers = new Product("SNICKERS");
        SingleProductPrice snickersPrice = new SingleProductPrice(snickers, pence(75));
    //        Then
        Assert.assertThat(snickersPrice.calculateFor(snickers, Quantity.items(1)), is(pence(75)));
        Assert.assertThat(snickersPrice.calculateFor(snickers, Quantity.items(2)), is(pence(150)));
        Assert.assertThat(snickersPrice.calculateFor(snickers, Quantity.items(0)), is(pence(0)));
    }

    @Test(expected = ProductAndPriceMismatch.class)
    public void reject_price_calculation_for_different_product() throws Exception {
    //        Given
        Product snickers = new Product("SNICKERS");
        Product mars = new Product("MARS");
        SingleProductPrice snickersPrice = new SingleProductPrice(snickers, pence(75));
    //        When
        snickersPrice.calculateFor(mars, Quantity.items(1));
    }


}
