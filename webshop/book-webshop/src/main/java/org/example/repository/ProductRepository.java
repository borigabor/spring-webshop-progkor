package org.example.repository;

import org.example.domain.order.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllProduct();

}
