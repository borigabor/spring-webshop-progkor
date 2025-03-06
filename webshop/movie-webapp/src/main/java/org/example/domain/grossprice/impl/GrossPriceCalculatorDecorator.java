package org.example.domain.grossprice.impl;

import org.example.domain.cart.ShoppingCartService;
import org.example.domain.grossprice.GrossPriceCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*Ide id rádobjuk a Componentet*/
@Component("grossPriceCalculatorDecorator")
public class GrossPriceCalculatorDecorator implements GrossPriceCalculator {

    private final GrossPriceCalculator grossPriceCalculator;

    /*Mivel a függőség úgy épül fel hogy a GrossPriceCalculatorDecorator-nak kell egy
    * GrossPriceCalculatorImpl ezért ebben az osztályban  a konstruktornál kell a @Qualifier annotáció
    * ez lehetővé teszi számunkra hogy a bean-nek megadott Qualifier név alapján be tudjuk injektálni
    * ezeket a bean-eket*/
    public GrossPriceCalculatorDecorator(@Qualifier("grossPriceCalculatorImpl") GrossPriceCalculator grossPriceCalculator) {
        this.grossPriceCalculator = grossPriceCalculator;
    }

    @Override
    public double getAggregatedGrossPrice(ShoppingCartService cartService) {
        return grossPriceCalculator.getAggregatedGrossPrice(cartService);
    }
}
