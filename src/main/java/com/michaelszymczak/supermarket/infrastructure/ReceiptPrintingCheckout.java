package com.michaelszymczak.supermarket.infrastructure;

import com.michaelszymczak.supermarket.domain.core.Product;
import com.michaelszymczak.supermarket.domain.price.PromotionalProductPrice;
import com.michaelszymczak.supermarket.domain.price.SingleProductPrice;
import com.michaelszymczak.supermarket.domain.price.WeightedProductPrice;
import com.michaelszymczak.supermarket.service.Checkout;
import com.michaelszymczak.supermarket.service.Printer;

import java.util.List;

public class ReceiptPrintingCheckout implements Checkout {
    public ReceiptPrintingCheckout(
            Printer printer,
            List<Product> boughtProducts,
            List<SingleProductPrice> singleProductPrices,
            List<WeightedProductPrice> weightedProductPrices,
            List<PromotionalProductPrice> promotionalProductPrices) {
    }
}
