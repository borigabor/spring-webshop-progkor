package org.example.domain.cart;

import org.example.domain.exception.NoSuchProductException;
import org.example.domain.order.Observable;
import org.example.domain.order.Observer;
import org.example.domain.order.model.Product;

import java.util.List;

public interface ShoppingCartService extends Observable {

    void order();

    double getTotalNetPrice();

    double getTotalGrossPrice();

    void addProduct(String productName) throws NoSuchProductException;

    List<Product> getProductsFromCart();

    void removeProduct(Product productToRemove);

    void subscribe(Observer observer);
}
