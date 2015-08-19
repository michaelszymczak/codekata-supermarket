package com.michaelszymczak.supermarket.domain.core;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class Receipt {

    private List<LineItem> lineItems;

    public Receipt(LineItem... lineItems) {
        this.lineItems = unmodifiableList(asList(lineItems));
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public Money getTotal() {
        return Money.pence(lineItems.stream()
                .map(LineItem::getPrice)
                .mapToLong(Money::getPence)
                .sum()
        );
    }
}
