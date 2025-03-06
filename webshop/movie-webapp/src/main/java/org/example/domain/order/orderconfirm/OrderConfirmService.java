package org.example.domain.order.orderconfirm;

import org.example.domain.order.Observer;
import org.example.domain.order.model.Cart;

public interface OrderConfirmService extends Observer {

    void sendOrderConfirmation(Cart cart);
}
