package org.example.domain.warehouse;

import org.example.domain.order.Observer;
import org.example.domain.order.model.Movie;

import java.util.List;

public interface Warehouse extends Observer {

    void registerOrderedMovies(List<Movie> movies);
}
