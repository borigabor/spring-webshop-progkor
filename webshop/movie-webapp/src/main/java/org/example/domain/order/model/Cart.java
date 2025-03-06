package org.example.domain.order.model;

import java.util.List;

public interface Cart {

    List<Movie> getMovies();

    void addMovie(Movie movie);

    void removeMovie(Movie movie);

    void clearCart();

}
