package org.example.domain.cart.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.cart.ShoppingCartService;
import org.example.domain.exception.NoSuchProductException;
import org.example.domain.order.Observer;
import org.example.domain.order.model.Cart;
import org.example.domain.order.model.Product;
import org.example.domain.order.model.impl.SimpleCart;
import org.example.repository.ProductRepository;
import org.example.repository.impl.StubProductRepository;

import java.util.List;

@Slf4j

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Cart cart = new SimpleCart();
    private final ProductRepository productRepository = new StubProductRepository();


    @Override
    public void order() {

    }

    @Override
    public double getTotalNetPrice() {
        return 0;
    }

    @Override
    public double getTotalGrossPrice() {
        return 0;
    }

    @Override
    public void addProduct(String productName) throws NoSuchProductException {
        // getAllProduct visszadja a listát
        Product productToAdd = productRepository.getAllProduct()
                .stream()
                .filter(product -> product.name().equals(productName))
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
                cart.addProduct(productToAdd);
    }

    @Override
    public List<Product> getProductsFromCart() {
        return List.of();
    }

    @Override
    public void removeProduct(Product productToRemove) {

    }

    @Override
    public void subscribe(Observer observer) {

    }
}
