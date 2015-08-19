package com.michaelszymczak.supermarket.domain.price;

import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.core.Quantity;
import org.junit.Before;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.core.Money.pence;
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
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.items(5), pence(200));
    //        Then
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.items(5)), is(pence(200)));
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.items(10)), is(pence(400)));
    }

    @Test
    public void calculate_only_price_for_quantity_above_the_threshold() throws Exception {
    //        Given
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.items(5), pence(200));
        //        Then
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.items(4)), is(pence(0)));
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.items(6)), is(pence(200)));
        assertThat(_5snickersPrice.calculateFor(snickers, Quantity.items(9)), is(pence(200)));
    }

    @Test(expected = ProductAndPriceMismatch.class)
    public void reject_price_calculation_for_different_product() throws Exception {
    //        Given
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.items(5), pence(200));
    //        When
        _5snickersPrice.calculateFor(mars, Quantity.items(5));
    }

    @Test
    public void provide_information_about_how_many_products_not_qualify() throws Exception {
    //        Given
        PromotionalProductPrice _5snickersPrice = new PromotionalProductPrice(snickers, Quantity.items(5), pence(200));
    //        Then
        assertThat(_5snickersPrice.notQualify(Quantity.items(5)), is(Quantity.items(0)));
        assertThat(_5snickersPrice.notQualify(Quantity.items(10)), is(Quantity.items(0)));
        assertThat(_5snickersPrice.notQualify(Quantity.items(1)), is(Quantity.items(1)));
        assertThat(_5snickersPrice.notQualify(Quantity.items(6)), is(Quantity.items(1)));
        assertThat(_5snickersPrice.notQualify(Quantity.items(9)), is(Quantity.items(4)));

    }


}
