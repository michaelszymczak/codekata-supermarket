package com.michaelszymczak.supermarket.domain.pricing;

import com.michaelszymczak.supermarket.domain.Money;
import com.michaelszymczak.supermarket.domain.Product;
import com.michaelszymczak.supermarket.domain.Quantity;
import com.michaelszymczak.supermarket.domain.Weight;
import com.michaelszymczak.supermarket.domain.price.PromotionalProductPrice;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;
import com.michaelszymczak.supermarket.domain.price.WeightedProductPrice;

import java.util.*;

public class ProductPricing {
    private final Map<Product, List<SingleProductPrice>> singleProductPricesByProduct;
    private final Map<Product, List<WeightedProductPrice>> weightedProductPricesByProduct;
    private final Map<Product, List<PromotionalProductPrice>> promotionalProductPricesByProduct;

    public ProductPricing(
            List<SingleProductPrice> singleProductPrices,
            List<WeightedProductPrice> weightedProductPrices,
            List<PromotionalProductPrice> promotionalProductPrices
    ) {
        this.singleProductPricesByProduct = new HashMap<>();
        for (SingleProductPrice singleProductPrice : singleProductPrices) {
            if ( ! singleProductPricesByProduct.containsKey(singleProductPrice.getProduct())) {
                singleProductPricesByProduct.put(singleProductPrice.getProduct(), new ArrayList<>());
            }
            singleProductPricesByProduct.get(singleProductPrice.getProduct()).add(singleProductPrice);
        }
        this.weightedProductPricesByProduct = new HashMap<>();
        for (WeightedProductPrice weightedProductPrice : weightedProductPrices) {
            if ( ! weightedProductPricesByProduct.containsKey(weightedProductPrice.getProduct())) {
                weightedProductPricesByProduct.put(weightedProductPrice.getProduct(), new ArrayList<>());
            }
            weightedProductPricesByProduct.get(weightedProductPrice.getProduct()).add(weightedProductPrice);
        }
        this.promotionalProductPricesByProduct = new HashMap<>();
        for (PromotionalProductPrice promotionalProductPrice : promotionalProductPrices) {
            if ( ! promotionalProductPricesByProduct.containsKey(promotionalProductPrice.getProduct())) {
                promotionalProductPricesByProduct.put(promotionalProductPrice.getProduct(), new ArrayList<>());
            }
            promotionalProductPricesByProduct.get(promotionalProductPrice.getProduct()).add(promotionalProductPrice);
        }
    }

    public LineItem lineItemOf(Product product, Quantity quantity) {
        Money price;
        if (promotionalProductPricesByProduct.containsKey(product)) {
            price = promotionalProductPricesByProduct.get(product).get(0).calculateFor(product, quantity);
        } else {
            price = singleProductPricesByProduct.get(product).get(0).calculateFor(product, quantity);
        }
        return LineItem.withQuantity(product, quantity, price);
    }

    public LineItem lineItemOf(Product product, Weight weight) {
        return LineItem.withWeight(product, weight, weightedProductPricesByProduct.get(product).get(0).calculateFor(product, weight));
    }
}
