package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.core.Quantity;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class SingleProductPricing {
    private final Map<Product, List<SingleProductPrice>> singleProductPricesByProduct;

    public SingleProductPricing(
            List<SingleProductPrice> singleProductPrices
    ) {
        this.singleProductPricesByProduct = singleProductPrices.stream()
                .collect(groupingBy(SingleProductPrice::getProduct));
    }

    public CurrentPriceAndRemainingQuantity calculate(CurrentPriceAndRemainingQuantity current) {
        if (current.noItemLeft()) {
            return current;
        }

        return new CurrentPriceAndRemainingQuantity(
                current.product,
                current.currentPriceAdding(singleProductPricesByProduct.get(current.product).get(0).calculateFor(current.product, current.quantity)),
                Quantity.items(0)
        );
    }

}
