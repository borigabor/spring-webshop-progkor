package org.example.repository.impl;

import org.example.domain.order.model.Movie;
import org.example.domain.order.model.impl.SimpleMovie;
import org.example.repository.MovieRepository;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    /*egy statikus és végleges (constant) listát definiál, amely különböző típusú almákat (Movie)
         tartalmaz SimpleMovie objektumokként ezeket nem lehet módosítani!.
         - List.of(...): Egy immutable (nem módosítható) listát hoz létre.
        - a lista elemei különböző példányok
        - a simpleMovie egy osztály ami egy filmet reprezentál
         */
    private static final List<Movie> MOVIES = List.of(
            new SimpleMovie("A majmok bolygólya", 2.99D),
            new SimpleMovie("Barbie", 6.25D),
            new SimpleMovie("Furiosa", 5.50D),
            new SimpleMovie("Alien: Romolus", 5.10D)
    );

/*Ez az annotáció azt jelzi, hogy a getAllMovie() metódust felüldefiniáljuk az
 MovieRepository interfészben megadott metódus alapján.*/

    @Override
    public List<Movie> getAllMovie() {
        return MOVIES;
    }
}
