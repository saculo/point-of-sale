package com.saculo.internship.output;

import com.saculo.internship.product.Product;

public interface OutputDevice {

    void print(String message);

    void printNameAndPriceOfProduct(Product product);
}
