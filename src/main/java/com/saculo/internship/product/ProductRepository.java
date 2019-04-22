package com.saculo.internship.product;

import com.saculo.internship.product.model.Product;

import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    void delete(Product product);

    Optional<Product> get(String barCode);

    boolean exists(String barCode);
}
