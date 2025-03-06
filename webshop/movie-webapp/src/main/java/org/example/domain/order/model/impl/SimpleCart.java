package org.example.domain.order.model.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.domain.order.model.Cart;
import org.example.domain.order.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
/*Itt szintén ellátjuk egy sztereotipikus annotációval. Innen vissza a ShoppingCartServiceImpl*/
@Component
public class SimpleCart implements Cart {

    /*Az arraylist-et nem bántjuk ugyanis ezek nem igazi bean-ek hanem csak
    * egyszerű típusok. Springesítés szempontjából nem éri meg valük foglalkozni.*/
    List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public void addMovie(final Movie movie) {
        movies.add(movie);
    }

    @Override
    public void removeMovie(final Movie movie) {
        movies.remove(movie);
    }

    @Override
    public void clearCart() {
        movies.clear();
    }
}
