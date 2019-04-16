package com.saculo.internship.product;

import java.util.HashMap;

public class ProductRepositoryImpl implements ProductRepositiory {

    private HashMap<String, Product> products = new HashMap<>();

    public Product save(Product product) {
        String barCode = product.getBarCode();
        return products.put(barCode, product);
    }

    public void delete(Product product) {
        String barCode = product.getBarCode();
        products.remove(barCode);
    }

    public Product get(String barCode) {
        return products.get(barCode);
    }

    public boolean exists(String barCode) {
        return products.containsKey(barCode);
    }
}
