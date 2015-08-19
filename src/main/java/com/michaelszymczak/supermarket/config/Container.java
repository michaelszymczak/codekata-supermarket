package com.michaelszymczak.supermarket.config;

import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.price.PromotionalProductPrice;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;
import com.michaelszymczak.supermarket.domain.price.WeightedProductPrice;
import com.michaelszymczak.supermarket.infrastructure.ConsolePrinter;
import com.michaelszymczak.supermarket.infrastructure.ReceiptPrintingCheckout;
import com.michaelszymczak.supermarket.service.Checkout;

import java.util.List;

public class Container {
    public static Checkout createCheckout(
            List<Product> products,
            List<SingleProductPrice> singleProductPrices,
            List<WeightedProductPrice> weightedProductPrices,
            List<PromotionalProductPrice> promotionalProductPrices
            ) {
        return new ReceiptPrintingCheckout(
                new ConsolePrinter(),
                products,
                singleProductPrices,
                weightedProductPrices,
                promotionalProductPrices
        );
    }
}
