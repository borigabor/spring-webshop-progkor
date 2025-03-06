package org.example.domain.grossprice.impl;

import lombok.NoArgsConstructor;
import org.example.domain.cart.ShoppingCartService;
import org.example.domain.grossprice.GrossPriceCalculator;
import org.springframework.stereotype.Component;

/*rádobjuk a component-et*/
@NoArgsConstructor
/*itt kell megadni ha név alapján injektáljuk be őket*/
@Component("grossPriceCalculatorImpl")
public class GrossPriceCalculatorImpl implements GrossPriceCalculator {

    @Override
    public double getAggregatedGrossPrice(ShoppingCartService cartService) {
        return cartService.getTotalNetPrice();
    }
}
