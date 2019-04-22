package com.saculo.internship;

import com.saculo.internship.device.input.Scanner;
import com.saculo.internship.device.input.InputDevice;
import com.saculo.internship.device.output.LCDScreen;
import com.saculo.internship.device.output.OutputDevice;
import com.saculo.internship.device.output.Printer;
import com.saculo.internship.product.model.Product;
import com.saculo.internship.product.ProductManager;
import com.saculo.internship.product.ProductRepository;
import com.saculo.internship.product.InMemoryProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductManagerTest {

    private ProductRepository productRepository;
    private InputDevice barCodeScanner;
    private List<OutputDevice> outputDevices;
    private ProductManager productManager;

    @BeforeEach
    public void init() {
        productRepository = new InMemoryProductRepository();
        barCodeScanner = new Scanner();
        outputDevices =  new ArrayList<>(Arrays.asList(new LCDScreen(), new Printer()));
        productManager = new ProductManager(productRepository, barCodeScanner, outputDevices);
        productRepository.save(new Product("Mleko", "1", 9.50));
        productRepository.save(new Product("Kanapka", "2", 4.50));
    }

    @Test
    public void shouldPrintMessageWhenBarCodeIsEmpty() {

        //given
        productManager.startTransaction();

        //when
        String message = productManager.scanCode("");

        //then
        assertEquals(message, "Invalid bar-code");
    }

    @Test
    public void shouldPrintMessageWhenProductIsNotFound() {

        //given
        productManager.startTransaction();
        productRepository.save(new Product("Mleko", "1", 9.50));
        productRepository.save(new Product("Kanapka", "2", 4.50));

        //when
        String message = productManager.scanCode("3");

        //then
        assertEquals(message, "Product not found");
    }

    @Test
    public void shouldPrintMessageWhenProductIsFound() {

        //given
        productManager.startTransaction();
        Product product = new Product("Mleko", "1", 9.50);
        productRepository.save(product);

        //when
        String message = productManager.scanCode("1");

        //then
        assertEquals(message, product.toString());
    }

    @Test
    public void shouldPrintFinalMessageOnExitBarCode() {

        //given
        productManager.startTransaction();
        productRepository.save(new Product("Mleko", "1", 9.50));
        productRepository.save(new Product("Kanapka", "2", 4.50));
        productManager.scanCode("1");
        productManager.scanCode("2");

        //when
        String message = productManager.scanCode("exit");

        //given
        productManager.startTransaction();
        productManager.scanCode("2");
        productManager.scanCode("2");
        productManager.scanCode("2");

        //when
        String message1 = productManager.scanCode("exit");

        //then
        assertEquals(message, "14.0");
        assertEquals(message1, "13.5");

    }


}
