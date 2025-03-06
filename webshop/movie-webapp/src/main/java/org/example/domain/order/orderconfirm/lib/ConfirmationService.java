package org.example.domain.order.orderconfirm.lib;

import org.example.domain.order.model.Movie;

import java.util.List;

public interface ConfirmationService {

    void sendConfirmationMessageAbout(List<Movie> movies);
}
