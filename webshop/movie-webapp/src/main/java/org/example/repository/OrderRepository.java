package org.example.repository;

import org.example.domain.order.model.Cart;

public interface OrderRepository {
    void saveOrder(Cart cart);
}
