package com.saculo.internship.product;

public interface ProductRepositiory {

    Product save(Product product);

    void delete(Product product);

    Product get(String barCode);

    boolean exists(String barCode);
}
