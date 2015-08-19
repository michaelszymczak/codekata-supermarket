package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.core.LineItem;
import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.price.PromotionalProductPrice;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;
import com.michaelszymczak.supermarket.domain.price.WeightedProductPrice;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.michaelszymczak.supermarket.domain.core.Money.pence;
import static com.michaelszymczak.supermarket.domain.core.Quantity.items;
import static com.michaelszymczak.supermarket.domain.core.Weight.grams;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductPricingShould {

    private Product snickers;
    private Product potatoes;
    private List<SingleProductPrice> NO_SINGLE_PRODUCT_PRICES = Collections.emptyList();
    private List<WeightedProductPrice> NO_WEIGHTED_PRODUCT_PRICES = Collections.emptyList();
    private List<PromotionalProductPrice> NO_PROMOTIONAL_PRODUCT_PRICES = Collections.emptyList();

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
                asList(snickersPrice), NO_WEIGHTED_PRODUCT_PRICES, NO_PROMOTIONAL_PRODUCT_PRICES
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
                NO_SINGLE_PRODUCT_PRICES, asList(potatoesPrice), NO_PROMOTIONAL_PRODUCT_PRICES
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
                NO_SINGLE_PRODUCT_PRICES, NO_WEIGHTED_PRODUCT_PRICES, asList(_4_snickersPrice)
        );
        //        When
        LineItem lineItem = pricing.lineItemOf(snickers, items(8));
        //        Then
        assertThat(lineItem, is(LineItem.withQuantity(snickers, items(8), pence(200))));
    }

    @Test
    public void combine_price_with_and_without_promotion_if_not_all_qualify() throws Exception {
        //        Given
        PromotionalProductPrice _4_snickersPrice = new PromotionalProductPrice(snickers, items(4), pence(100));
        SingleProductPrice singleSnickersPrice = new SingleProductPrice(snickers, pence(30));
        ProductPricing pricing = new ProductPricing(
                asList(singleSnickersPrice), NO_WEIGHTED_PRODUCT_PRICES, asList(_4_snickersPrice)
        );
        //        When
        LineItem lineItem = pricing.lineItemOf(snickers, items(11));
        //        Then
        assertThat(lineItem, is(LineItem.withQuantity(snickers, items(11), pence(2 * 100 + 3 * 30))));
    }

    @Test
    public void combine_several_promotions() throws Exception {
        //        Given
        PromotionalProductPrice _10_snickersPrice = new PromotionalProductPrice(snickers, items(10), pence(200));
        PromotionalProductPrice _4_snickersPrice = new PromotionalProductPrice(snickers, items(4), pence(100));
        ProductPricing pricing = new ProductPricing(
                NO_SINGLE_PRODUCT_PRICES, NO_WEIGHTED_PRODUCT_PRICES, asList(_10_snickersPrice, _4_snickersPrice)
        );
        //        When
        LineItem lineItem = pricing.lineItemOf(snickers, items(28));
        //        Then
        assertThat(lineItem, is(LineItem.withQuantity(snickers, items(28), pence(2 * 200 + 2 * 100))));
    }
}
