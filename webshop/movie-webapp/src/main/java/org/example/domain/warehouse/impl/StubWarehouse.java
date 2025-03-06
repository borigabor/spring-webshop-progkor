package org.example.domain.warehouse.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.order.model.Cart;
import org.example.domain.order.model.Movie;
import org.example.domain.warehouse.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StubWarehouse implements Warehouse {


    @Override
    public void registerOrderedMovies(List<Movie> movies) {
        log.info("I have registered the order of movies {}", movies);
    }

    @Override
    public void notify(Cart cart) {
        registerOrderedMovies(cart.getMovies());
    }
}
