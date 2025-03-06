package org.example.domain.order.orderconfirm.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.order.model.Cart;
import org.example.domain.order.orderconfirm.OrderConfirmService;
import org.example.domain.order.orderconfirm.lib.ConfirmationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class EmailConfirmationAdapter implements OrderConfirmService {

    private ConfirmationService emailConfirmationService;

    @Override
    public void notify(final Cart cart) {
        log.info("Order confirmation, email adapter, cart: {}", cart);
        sendOrderConfirmation(cart);
    }


    @Override
    public void sendOrderConfirmation(final Cart cart) {
        emailConfirmationService.sendConfirmationMessageAbout(cart.getMovies());
    }

}
