package com.saculo.internship.product;

import com.saculo.internship.product.model.Product;

import java.util.HashMap;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {

    private HashMap<String, Product> products = new HashMap<>();

    public Product save(Product product) {
        String barCode = product.getBarCode();
        return products.put(barCode, product);
    }

    public void delete(Product product) {
        String barCode = product.getBarCode();
        products.remove(barCode);
    }

    public Optional<Product> get(String barCode) {
        return Optional.ofNullable(products.get(barCode));
    }

    public boolean exists(String barCode) {
        return products.containsKey(barCode);
    }
}
