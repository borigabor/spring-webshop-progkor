package org.example.domain.grossprice;

import org.example.domain.cart.ShoppingCartService;

/*Bruttó ár*/
public interface GrossPriceCalculator {

    double getAggregatedGrossPrice(ShoppingCartService cartService);
}
