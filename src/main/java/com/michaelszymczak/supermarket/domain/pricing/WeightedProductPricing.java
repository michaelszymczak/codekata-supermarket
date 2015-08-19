package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.core.Money;
import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.core.Weight;
import com.michaelszymczak.supermarket.domain.price.WeightedProductPrice;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class WeightedProductPricing {
    private final Map<Product, List<WeightedProductPrice>> weightedProductPricesByProduct;

    public WeightedProductPricing(List<WeightedProductPrice> weightedProductPrices) {
        this.weightedProductPricesByProduct = weightedProductPrices.stream()
                .collect(groupingBy(WeightedProductPrice::getProduct));
    }

    public Money calculate(Product product, Weight weight) {
        return weightedProductPrice(product).calculateFor(product, weight);
    }


    private WeightedProductPrice weightedProductPrice(Product product) {
        return weightedProductPricesByProduct.get(product).get(0);
    }

}
