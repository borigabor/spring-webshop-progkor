package org.example.domain.cart;

import org.example.domain.exception.NoSuchMovieException;
import org.example.domain.order.Observable;
import org.example.domain.order.Observer;
import org.example.domain.order.model.Cart;
import org.example.domain.order.model.Movie;

import java.util.List;

public interface ShoppingCartService extends Observable {

    void addMovie (String movieTitle) throws NoSuchMovieException;

    void order();

    double getTotalNetPrice();

    double getTotalGrossPrice();

    List<Movie> getMoviesFromCart();

    void removeMovie(Movie movieToRemove);

    double getBasePrice();

    void subscribe(Observer observer);
}
