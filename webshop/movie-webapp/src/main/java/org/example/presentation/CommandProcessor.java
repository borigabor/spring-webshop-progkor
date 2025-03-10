package org.example.presentation;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.cart.ShoppingCartService;
import org.example.domain.cart.impl.ShoppingCartServiceImpl;
import org.example.domain.exception.NoSuchMovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Elhelyezzük rajta a Component nevű annotációt*/



@Slf4j
/*Component ez egy stereotype annotáció ez azt jelenti hogy a legelsők között inicializálva kell
hogy legyen hogy a dependecy injection müködni tudjo és egymásba lehessen injektáln ezeket a bean-eket,
objektumokat.*/
@Component
public class CommandProcessor {

    /*megyünk tovább a fügőségi hierarchiában. A CommandProcessot függősége a ShoppingCartService
    * implementáció*/

    /*
    * itt szintén megszabadultunk az explicit objektum készítéstől
    * private final ShoppingCartService shoppingCartService = ShoppingCartServiceImpl();
    * */
    private final ShoppingCartService shoppingCartService;

    /*Nyavajogni fog hogy nincs konstruktor ugy hogy csinálunk egyet*/

    /*Autowired több konstruktor esetében érdemes kitenni
    * hogy el tudja dönteni meik az amit alapértelmezetten használnia kell. Ha egy
    * konstruktora  van az osztálynak akkor nem szükséges*/
    //@Autowired
    public CommandProcessor(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public void process() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("We don't needed CommandProcessor class");
        System.out.println("Welcome enter a command:");

        while(running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                running = false;
                System.out.println("Exiting program...");
            } else if (input.matches("add movie .+")) {
                handleAddMovie(input);
            } else if (input.equalsIgnoreCase("order cart")){
                handleOrderCart();
            } else {
                System.out.println("Unknown command. Try Again");
            }
        }
        scanner.close();

    }

    private void handleAddMovie(String input) {
        Pattern pattern = Pattern.compile("add movie (.+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String movieTitle = matcher.group(1);
            try {
                shoppingCartService.addMovie(movieTitle);
                System.out.println("Movie added successfully..." + movieTitle);
            } catch (NoSuchMovieException e) {
                System.out.println("Movie not found: " + movieTitle);
            }
        }
    }

    private void handleOrderCart() {
        shoppingCartService.order();
        System.out.println("Order placed successfully...");
    }


}
