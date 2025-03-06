package org.example.domain.order;

import org.example.domain.order.model.Cart;


public interface Observer  {
    void notify(Cart cart);
}
