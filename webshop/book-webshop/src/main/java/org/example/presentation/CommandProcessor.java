package org.example.presentation;

import org.example.domain.cart.ShoppingCartService;
import org.example.domain.cart.impl.ShoppingCartServiceImpl;
import org.example.domain.exception.NoSuchProductException;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {

    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();

    public void process() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome! Enter a commend:");

        while(running) {
            System.out.print("> ");

            // trim a string előtt és utén kiszedi a szóközt
            String input = scanner.nextLine().trim();

            // A kis és anygbetű közötti külömbségeket ignorálja.
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                running = false;
            } else if (input.matches("add product .+")) {
                handleAddProduct(input);
            } else if (input.equalsIgnoreCase("order cart")) {
                handleOrderCart();
            } else {
                System.out.println("Unknown command. Try again.");
            }
        }
        scanner.close();

    }

    public void handleAddProduct(String input) {

        // Egy keresési mintát definiál.
        Pattern pattern = Pattern.compile("add product (.+)");
        // A keresési minta keresésére szolgál.
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()) {
            String productName = matcher.group(1);
            try {
                shoppingCartService.addProduct(productName);
                System.out.println("Product added: " + productName);
            } catch (NoSuchProductException e) {
                System.out.println("Product not found: " + productName);
            }
        }

    }

    public void handleOrderCart() {
        System.out.println("Order placed successfully...");
    }
}
