package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.price.PromotionalProductPrice;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class PromotionalProductPricing {
    private final Map<Product, List<PromotionalProductPrice>> promotionalProductPricesByProduct;

    public PromotionalProductPricing(
            List<PromotionalProductPrice> promotionalProductPrices
    ) {
        this.promotionalProductPricesByProduct = promotionalProductPrices.stream()
                .collect(groupingBy(PromotionalProductPrice::getProduct));
    }

    public CurrentPriceAndRemainingQuantity calculate(CurrentPriceAndRemainingQuantity current) {
        if (!promotionalProductPricesByProduct.containsKey(current.product)) {
            return current;
        }

        for (PromotionalProductPrice promotionalPrice : promotionalPriceFor(current.product)) {
            current = getCurrentPriceAndRemainingQuantity(current, promotionalPrice);
        }

        return current;
    }

    private CurrentPriceAndRemainingQuantity getCurrentPriceAndRemainingQuantity(CurrentPriceAndRemainingQuantity current, PromotionalProductPrice promotionalPrice) {
        return new CurrentPriceAndRemainingQuantity(
                current.product,
                current.price.adding(promotionalPrice.calculateFor(current.product, current.quantity)),
                promotionalPrice.notQualify(current.quantity)
        );
    }

    private List<PromotionalProductPrice> promotionalPriceFor(Product product) {
        return promotionalProductPricesByProduct.get(product);
    }


}
