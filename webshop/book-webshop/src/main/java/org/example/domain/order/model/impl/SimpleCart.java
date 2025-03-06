package org.example.domain.order.model.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.domain.order.model.Cart;
import org.example.domain.order.model.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode

public class SimpleCart implements Cart {

    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(final Product product) {
        products.add(product);
    }

    @Override
    public void removeProduct(Product product) {

    }

    @Override
    public void clearCart() {

    }
}
