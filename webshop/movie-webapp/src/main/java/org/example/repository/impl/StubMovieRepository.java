package org.example.repository.impl;

import org.example.domain.order.model.Movie;
import org.example.domain.order.model.impl.SimpleMovie;
import org.example.repository.MovieRepository;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*Az osztály, amely megvalósítja (implements) a MovieRepository interfészt.*/

/*repositorynál a repository annotációt azért jó mert átcsomagolja az adatbázisok által dobott
kivételeket springes kivételeké. De mivel most nem ilyenekkel dolgozunk ezért simán a Componentet használjuk
Vissza ShoppingCartServiceImpl
* */
//@Repository
@Component
public class StubMovieRepository implements MovieRepository {

    /*Kölcsönös függőség
    beinjektáljuk a az orderRepositoryt
    private final OrderRepository orderRepository;

    public StubMovieRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    */

    private final List<Movie> movies;

    public StubMovieRepository() {
        movies = new ArrayList<>();
        movies.add(new SimpleMovie(1L,"A majmok bolygólya", 2.99D));
        movies.add(new SimpleMovie(2L, "Barbie", 6.25D));
        movies.add(new SimpleMovie(3L, "Furiosa", 5.50D));
        movies.add(new SimpleMovie(4L, "Alien: Romolus", 5.10D));
    }

    /*egy statikus és végleges (constant) listát definiál, amely különböző típusú almákat (Movie)
         tartalmaz SimpleMovie objektumokként ezeket nem lehet módosítani!.
         - List.of(...): Egy immutable (nem módosítható) listát hoz létre.
        - a lista elemei különböző példányok
        - a simpleMovie egy osztály ami egy filmet reprezentál
            private static final List<Movie> MOVIES = List.of(
            new SimpleMovie("A majmok bolygólya", 2.99D),
            new SimpleMovie("Barbie", 6.25D),
            new SimpleMovie("Furiosa", 5.50D),
            new SimpleMovie("Alien: Romolus", 5.10D)
    );
         */



/*Ez az annotáció azt jelzi, hogy a getAllMovie() metódust felüldefiniáljuk az
 MovieRepository interfészben megadott metódus alapján.*/

    @Override
    public List<Movie> findAllMovies() {
        return movies;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        long movieId = generateNextId();
        SimpleMovie movieToSave = new SimpleMovie(movieId, movie.title(), movie.netPrice());
        movies.add(movieToSave);
        return movieToSave;
    }

    @Override
    public Movie findMovie(Long id) {
        return movies.stream()
                .filter(movie -> movie.id().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }

    @Override
    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = findMovie(id);
        SimpleMovie updateSimpleMovie = new SimpleMovie(id, movieDetails.title(), movieDetails.netPrice());
        movie.title();
        movie.netPrice();
        deleteMovie(id);
        movies.add(updateSimpleMovie);
        return updateSimpleMovie;
    }

    @Override
    public void deleteMovie(Long id) {
        movies.removeIf(movie -> movie.id().equals(id));
    }

    /*végig megy a listán és megnézi hogy melyik a legnagyobb érték
    * az azonsoítók közül és ahhoz hozzáad plussz eggyet*/
    private long generateNextId() {
        return movies.stream().mapToLong(Movie::id).max().orElse(0L) + 1;
    }
}
