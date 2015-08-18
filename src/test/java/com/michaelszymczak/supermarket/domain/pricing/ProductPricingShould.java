package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.Money.pence;
import static com.michaelszymczak.supermarket.domain.Quantity.items;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public class ProductPricingShould {

    private Product snickers;

    @Before
    public void setUp() {
        snickers = Product.ofCode("SNICKERS");
    }

    @Test
    public void calculate_how_much_to_pay_for_a_product() throws Exception {
    //        Given
        SingleProductPrice snickersPrice = new SingleProductPrice(snickers, pence(75));
        ProductPricing pricing = new ProductPricing(asList(snickersPrice));
    //        When
        LineItem lineItem = pricing.lineItemOf(snickers, items(2));
    //        Then
        Assert.assertThat(lineItem, Matchers.is(new LineItem(snickers, items(2), pence(150))));
    }
}
