package com.saculo.internship.output;

import com.saculo.internship.product.Product;

public class LCDScreen implements OutputDevice {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printNameAndPriceOfProduct(Product product) {
        print(product.getName() + " " + product.getPrice());
    }

    public void printIfProductNotFound() {
        print("Product not found");
    }

    public void printIfBarCodeIsNull() {
        print("Bar code is invalid");
    }
}
