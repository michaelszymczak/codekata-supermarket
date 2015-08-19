package com.michaelszymczak.supermarket.domain.core;

import org.junit.Assert;
import org.junit.Test;

import static com.michaelszymczak.supermarket.domain.core.Money.pence;
import static com.michaelszymczak.supermarket.domain.core.Quantity.items;
import static com.michaelszymczak.supermarket.domain.core.Weight.grams;

public class ReceiptShould {

    @Test
    public void summarize_all_line_items() throws Exception {
    //        When
        Receipt receipt = new Receipt(
                LineItem.withQuantity(Product.ofCode("SNICKERS"), items(5), pence(100)),
                LineItem.withWeight(Product.ofCode("CARROT"), grams(200), pence(50))
        );
    //        Then
        Assert.assertEquals(2, receipt.getLineItems().size());
        Assert.assertEquals(pence(150), receipt.getTotal());
    }
}