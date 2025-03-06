package org.example.domain.cart.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.cart.ShoppingCartService;
import org.example.domain.exception.NoSuchMovieException;
import org.example.domain.grossprice.impl.GrossPriceCalculatorDecorator;
import org.example.domain.grossprice.impl.GrossPriceCalculatorImpl;
import org.example.domain.grossprice.impl.HungarianTaxGrossPriceCalculator;
import org.example.domain.order.Observer;
import org.example.domain.order.model.Cart;
import org.example.domain.order.model.Movie;
import org.example.domain.order.model.impl.SimpleCart;
import org.example.domain.order.orderconfirm.impl.EmailConfirmationAdapter;
import org.example.domain.order.orderconfirm.impl.StubOrderConfirmationService;
import org.example.domain.order.orderconfirm.lib.impl.EmailConfirmationService;
import org.example.domain.warehouse.impl.StubWarehouse;
import org.example.repository.MovieRepository;
import org.example.repository.OrderRepository;
import org.example.repository.impl.StubMovieRepository;
import org.example.repository.impl.StubOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
/*Service amely a Component annotációnak egy speciálisabb változata (Vissz a CommandProcessorba )*/
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    /*haladunk tovább lefelé a hierarchia fán és megnézzük hogy a ShoppingCartServiceImpl milyen
    * más függőségei vannak.*/

    /*ugyanaz az eljrás mint az előbb
     private final Cart cart = new SimpleCart();
     explicit objektum készítéstöl megszabadulunk, létrehozunk
     egy konstruktort stb */
    private final Cart cart;

    /*soron következő függőség*/
    /*ugyanaz az eljrás mint az előbb
     private final MovieRepository movieRepository = new StubMovieRepository();
     explicit objektum készítéstöl megszabadulunk, létrehozunk
     egy konstruktort stb*/
    private final MovieRepository movieRepository;

    /*soron következő függőség*/
        /*ugyanaz az eljrás mint az előbb
    private final OrderRepository orderRepository = new StubOrderRepository();
     explicit objektum készítéstöl megszabadulunk, létrehozunk
     egy konstruktort stb*/
    private final OrderRepository orderRepository;


/*A hiearhiát itt is legalulról kell kezdenünk tehát GrossPriceCalculatorImpl() a soron következő
* következő függőség GrossPriceCalculatorDecorator
* következő függőség HungarianTaxGrossPriceCalculator
* Ez nem fog müködni a megszokott módszerrel mert a spring nem tudja eldönteni több különböző bean,
* objektum küzül mejikre van szüksége az osztályunknak. Ez azért van mert a Spring alapesetben
* típus alapján keresi meg a függőségeket és az alapján bróbálja beinjektálni a függőséget magát.
* Ebben az esetben 2 osztályt is talált aminek ugyanaz a típusa inteface alapján. Így nem tudja eldönteni
* meikre van szükségünk
* Megoldás: bármilyen id-t megadhatunk ezeknek a bean -eknek: Nem typus alapján próbáljuk
* beinjektálni őket hanem inkább név alapján illetve azonosító alapján
* Összeségben a GrossPriceCalculatorImpl és a GrossPriceCalculatorDecorator azonosítótt adtunk meg és a
* GrossPriceCalculatorDecorator-ban hivatkoznunk kell a GrossPriceCalculatorImplementációnak az
* azonosítójára majd a HungarianTaxGrossPriceCalculator-ben hivatkoznunk kellet a GrossPriceCalculatorDecorator
* objektumra az azonositóra amit megadtunk.
* */
    private final HungarianTaxGrossPriceCalculator grossPriceCalculatorDecorator; // = new HungarianTaxGrossPriceCalculator(new GrossPriceCalculatorDecorator(new GrossPriceCalculatorImpl()));



    /*Observerek: A Spring képes arra alapvetően hogy typus alapján keriti elő az objektumokat és
    * legtöbször figyelembe vesziaz interfae típust. Tehát képes arra hogy interface alapján összedje
    * nekünk az összes interface típus amire az observer alá tartozik és egy listába beinjektálja azokat.
    * */
    private final List<Observer> observers;

    /*Csináltunk egy új konstruktor amiben az összes mező ott van*/

    public ShoppingCartServiceImpl(Cart cart, MovieRepository movieRepository, OrderRepository orderRepository, HungarianTaxGrossPriceCalculator grossPriceCalculatorDecorator, List<Observer> observers) {
        this.cart = cart;
        this.movieRepository = movieRepository;
        this.orderRepository = orderRepository;
        this.grossPriceCalculatorDecorator = grossPriceCalculatorDecorator;
        this.observers = observers;
    }

    /*életciklus fázis: 2 lépésnél van beleszólásunk
    * azután hogy létrejön a bean-ünk
    * 1. fel annotáljuk a PostConstruct nevü metódussal */
    @PostConstruct
    public void populate() {
        log.info("Successfully populated shopping cart...");
    }

    /* 2. PreDestroy annotáció ez meg közvetlenül akkor fog lefutni
    * mielőtt megsemmisülne az objektumunk*/
    @PreDestroy
    public void cleanUp() {
        log.info("Clean up shopping cart...");
    }

    @Override
    public void addMovie(String movieTitle) throws NoSuchMovieException {
        Movie movieToAdd = movieRepository.getAllMovie()
                .stream()
                .filter(movie -> movie.title().equals(movieTitle))
                .findFirst()
                .orElseThrow(NoSuchMovieException::new);
        cart.addMovie(movieToAdd);
    }

    @Override
    public void order() {
        orderRepository.saveOrder(cart);
        log.info("Order Net price: {} ", getTotalNetPrice());
        log.info("Order TotalGross price: {} ", getTotalGrossPrice());
        observers.forEach(observer -> observer.notify(cart));
        cart.clearCart();
    }

    @Override
    public double getTotalNetPrice() {
        return getBasePrice();
    }

    @Override
    public List<Movie> getMoviesFromCart() {
        return cart.getMovies();
    }

    @Override
    public void removeMovie(Movie movieToRemove) {
        cart.removeMovie(movieToRemove);
    }

    @Override
    public double getTotalGrossPrice() {
        return grossPriceCalculatorDecorator.getAggregatedGrossPrice(this);
    }

    @Override
    public double getBasePrice() {
        double basePrice = 0;
        for (Movie currentMovie : cart.getMovies()) {
            basePrice += currentMovie.netPrice();
        }
        return basePrice;
    }

    @Override
    public void subscribe(final Observer observer) {
        observers.add(observer);
    }
}
