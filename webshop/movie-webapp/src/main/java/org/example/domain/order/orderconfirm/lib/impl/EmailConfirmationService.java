package org.example.domain.order.orderconfirm.lib.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.order.model.Movie;
import org.example.domain.order.orderconfirm.lib.ConfirmationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EmailConfirmationService implements ConfirmationService {


    @Override
    public void sendConfirmationMessageAbout(List<Movie> movies) {
        log.info("Sending an e-mail confirmation about {} movies", movies);
    }
}
