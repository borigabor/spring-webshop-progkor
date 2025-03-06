package org.example.domain.order.model.impl;

import lombok.Builder;
import org.example.domain.order.model.Product;

@Builder
public record SimpleProduct(String name, double netPrice) implements Product {



}
