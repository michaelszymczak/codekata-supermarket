package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.price.PromotionalProductPrice;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;
import com.michaelszymczak.supermarket.domain.price.WeightedProductPrice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.michaelszymczak.supermarket.domain.Money.pence;
import static com.michaelszymczak.supermarket.domain.Quantity.items;
import static com.michaelszymczak.supermarket.domain.Weight.grams;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductPricingShould {

    private Product snickers;
    private Product potatoes;

    @Before
    public void setUp() {
        snickers = Product.ofCode("SNICKERS");
        potatoes = Product.ofCode("POTATO");
    }

    @Test
    public void calculate_how_much_to_pay_for_a_product_without_promotion() throws Exception {
    //        Given
        SingleProductPrice snickersPrice = new SingleProductPrice(snickers, pence(75));
        ProductPricing pricing = new ProductPricing(
                asList(snickersPrice), Collections.emptyList(), Collections.emptyList()
        );
    //        When
        LineItem lineItem = pricing.lineItemOf(snickers, items(2));
    //        Then
        assertThat(lineItem, is(LineItem.withQuantity(snickers, items(2), pence(150))));
    }

    @Test
    public void calculate_how_much_to_pay_for_a_weighted_product() throws Exception {
    //        Given
        WeightedProductPrice potatoesPrice = new WeightedProductPrice(potatoes, grams(1000), pence(700));
        ProductPricing pricing = new ProductPricing(
                Collections.emptyList(), asList(potatoesPrice), Collections.emptyList()
        );
    //        When
        LineItem lineItem = pricing.lineItemOf(potatoes, grams(1500));
    //        Then
        assertThat(lineItem, is(LineItem.withWeight(potatoes, grams(1500), pence(1050))));
    }

    @Test
    public void calculate_how_much_to_pay_for_a_product_in_promotion() throws Exception {
        //        Given
        PromotionalProductPrice _4_snickersPrice = new PromotionalProductPrice(snickers, items(4), pence(100));
        ProductPricing pricing = new ProductPricing(
                Collections.emptyList(), Collections.emptyList(), asList(_4_snickersPrice)
        );
        //        When
        LineItem lineItem = pricing.lineItemOf(snickers, items(8));
        //        Then
        assertThat(lineItem, is(LineItem.withQuantity(snickers, items(8), pence(200))));
    }
}
