package org.example.domain.order.model.impl;

import lombok.Builder;
import org.example.domain.order.model.Movie;

@Builder
public record SimpleMovie(String title, double netPrice) implements Movie {
}
