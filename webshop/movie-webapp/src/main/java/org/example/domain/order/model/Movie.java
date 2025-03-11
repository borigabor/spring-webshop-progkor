package org.example.domain.order.model;

/*Ebben az interface-ban egy film absztrakt reprezentációját adjuk meg.*/

public interface Movie {

    Long id();

    //Egy metódus, amely visszaadja a film címét (String típusban).
    String title();

    //Egy metódus, amely a film nettó árát (double típusban) adja vissza.
    double netPrice();
}
