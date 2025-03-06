package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final List<SimpleProduct> PRODUCTS = List.of(
            new SimpleProduct("Jonagold Apple", 2.99D),
            new SimpleProduct("Pink Lady Apple", 6.25D),
            new SimpleProduct("Honeycrisp Apple", 4.50D),
            new SimpleProduct("Fuji Apple", 5.10D),
            new SimpleProduct("Jazz Apple", 2.0D),
            new SimpleProduct("Gala Apple", 3.25D),
            new SimpleProduct("Golden Delicious Apple", 3.45D)
    );

    private final SimpleProduct product = new SimpleProduct();

    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome! Enter a command:");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                running = false;
            } else if (input.matches("add product .+")) {
                handleAddProduct(input);
            }  else {
                System.out.println("Unknown command. Try again.");
            }
        }
        scanner.close();
       
    }

    static public void handleAddProduct(String input) {
        Pattern pattern = Pattern.compile("add product (.+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String productName = matcher.group(1);
            PRODUCTS
                    .stream()
                    .filter( product -> product.getName().equals(productName))
                    .forEach(System.out::println);
        }
    }

}

