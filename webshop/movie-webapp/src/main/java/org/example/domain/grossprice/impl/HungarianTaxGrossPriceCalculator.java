package org.example.domain.grossprice.impl;

import org.example.domain.cart.ShoppingCartService;
import org.example.domain.grossprice.GrossPriceCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*rádobjuk a Componentet*/
@Component
public class HungarianTaxGrossPriceCalculator extends GrossPriceCalculatorDecorator{

    /*Ugyanaz az eljárás mint az előzőben*/
    public HungarianTaxGrossPriceCalculator(@Qualifier("grossPriceCalculatorDecorator") GrossPriceCalculator grossPriceCalculator) {
        super(grossPriceCalculator);
    }

    private static final double TAX_RATE = 1.27;

    @Override
    public double getAggregatedGrossPrice(ShoppingCartService cartService) {
        return super.getAggregatedGrossPrice(cartService) * TAX_RATE;
    }
}
