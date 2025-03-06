package org.example.domain.order.model;


import java.util.List;

public interface Cart {

    List<Product> getProducts();
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearCart();
}
