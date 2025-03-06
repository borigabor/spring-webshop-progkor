package org.example.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.order.model.Cart;
import org.example.repository.MovieRepository;
import org.example.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Slf4j

/*Itt is rádobtuk a Component annotációt*/
@Component
public class StubOrderRepository implements OrderRepository {


    /* Kölcsönös függőség
    Az order repository-ba pedig beinjektáljuk a MovieRepository-t

    private final MovieRepository movieRepository;

    /* körkörös függőség feloldása a Lazy annotációval
    * ami azt mondja hogy ráér majd később beinjektálni ezt a függőséget
    * majd amikor tényleg szükségünk lesz rá (töbnyire nem éri meg használni ezt az annotációt)
    public StubOrderRepository(@Lazy MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    */


    @Override
    public void saveOrder(Cart cart) {
        log.info("Saving order {}", cart);
    }
}
