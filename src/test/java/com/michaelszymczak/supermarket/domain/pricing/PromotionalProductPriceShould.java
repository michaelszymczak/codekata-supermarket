package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.pricing.Money.ofPence;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PromotionalProductPriceShould {

    private Product snickers;
    private Product mars;

    @Before
    public void setUp() {
        snickers = new Product("SNICKERS");
        mars = new Product("MARS");
    }

    @Test
    public void calculate_price_for_multiple_products() throws Exception {
    //        Given
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.of(5), ofPence(200));
    //        Then
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(5)), is(ofPence(200)));
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(10)), is(ofPence(400)));
    }

    @Test
    public void calculate_only_price_for_quantity_above_the_threshold() throws Exception {
    //        Given
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.of(5), ofPence(200));
        //        Then
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(4)), is(ofPence(0)));
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(6)), is(ofPence(200)));
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.of(9)), is(ofPence(200)));
    }

    @Test(expected = ProductAndPriceMismatch.class)
    public void reject_price_calculation_for_different_product() throws Exception {
    //        Given
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.of(5), ofPence(200));
    //        When
        _5snickersPrice.calculateFor(mars, Quantity.of(5));
    }

    @Test
    public void provide_information_about_how_many_products_not_qualify() throws Exception {
    //        Given
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.of(5), ofPence(200));
    //        Then
        assertThat(_5snickersPrice.notQualify(Quantity.of(5)), is(Quantity.of(0)));
        assertThat(_5snickersPrice.notQualify(Quantity.of(10)), is(Quantity.of(0)));
        assertThat(_5snickersPrice.notQualify(Quantity.of(1)), is(Quantity.of(1)));
        assertThat(_5snickersPrice.notQualify(Quantity.of(6)), is(Quantity.of(1)));
        assertThat(_5snickersPrice.notQualify(Quantity.of(9)), is(Quantity.of(4)));

    }


}
