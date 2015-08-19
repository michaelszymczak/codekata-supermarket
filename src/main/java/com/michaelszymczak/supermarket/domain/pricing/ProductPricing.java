package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.core.*;
import com.michaelszymczak.supermarket.domain.price.PromotionalProductPrice;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;
import com.michaelszymczak.supermarket.domain.price.WeightedProductPrice;

import java.util.List;

import static com.michaelszymczak.supermarket.domain.pricing.CurrentPriceAndRemainingQuantity.initiateForProductAndQuantity;

public class ProductPricing {
    private final SingleProductPricing singleProductPricing;
    private final PromotionalProductPricing promotionalProductPricing;
    private final WeightedProductPricing weightedProductPricing;

    public ProductPricing(
            List<SingleProductPrice> singleProductPrices,
            List<WeightedProductPrice> weightedProductPrices,
            List<PromotionalProductPrice> promotionalProductPrices
    ) {
        singleProductPricing = new SingleProductPricing(singleProductPrices);
        promotionalProductPricing = new PromotionalProductPricing(promotionalProductPrices);
        weightedProductPricing = new WeightedProductPricing(weightedProductPrices);
    }

    public LineItem lineItemOf(Product product, Quantity quantity) {
        return LineItem.withQuantity(product, quantity, quantifiedProductPrice(product, quantity));
    }

    public LineItem lineItemOf(Product product, Weight weight) {
        return LineItem.withWeight(product, weight, weightedProductPrice(product, weight));
    }

    private Money quantifiedProductPrice(Product product, Quantity quantity) {
        return singleProductPricing.calculate(
                promotionalProductPricing.calculate(initiateForProductAndQuantity(product, quantity))
        ).price;
    }

    private Money weightedProductPrice(Product product, Weight weight) {
        return weightedProductPricing.calculate(product, weight);
    }

}
