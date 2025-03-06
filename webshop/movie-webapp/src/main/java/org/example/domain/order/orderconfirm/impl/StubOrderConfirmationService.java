package org.example.domain.order.orderconfirm.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.order.model.Cart;
import org.example.domain.order.orderconfirm.OrderConfirmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StubOrderConfirmationService implements OrderConfirmService {

    @Override
    public void sendOrderConfirmation(Cart cart) {
        log.info("An order confirmation from cart {} had been sent, ", cart);
    }

    @Override
    public void notify(Cart cart) {
        sendOrderConfirmation(cart);
    }
}
