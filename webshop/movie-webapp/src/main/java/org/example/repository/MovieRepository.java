package org.example.repository;

import org.example.domain.order.model.Movie;

import java.util.List;

/*MovieRepository interface a domain/order/model
 Egy metódust deklarál, amely egy List<Movie> típusú listát ad vissza. */

public interface MovieRepository {

    List<Movie> findAllMovies();

    Movie saveMovie(Movie movie);

    Movie findMovie(Long id);

    Movie updateMovie(Long id, Movie movieDetails);

    void deleteMovie(Long id);

}
