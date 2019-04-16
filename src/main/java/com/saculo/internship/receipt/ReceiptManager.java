package com.saculo.internship.receipt;

import com.saculo.internship.input.BarCodeScanner;
import com.saculo.internship.output.LCDScreen;
import com.saculo.internship.output.Printer;
import com.saculo.internship.product.Product;
import com.saculo.internship.product.ProductRepositiory;
import com.saculo.internship.product.ProductRepositoryImpl;

public class ReceiptManager {

    private ProductRepositiory productRepositiory = new ProductRepositoryImpl();
    private BarCodeScanner barCodeScanner = new BarCodeScanner(productRepositiory);
    private Printer printer = new Printer();
    private LCDScreen lcdScreen = new LCDScreen();

    public void startTransaction() {
        productRepositiory.save(new Product("Mleko", "1", 9.50));
        productRepositiory.save(new Product("Kanapka", "2", 4.50));
        Receipt receipt = new Receipt();
        while(true) {
            String barCode = barCodeScanner.scanBarCode();
            if(barCode.equals("exit"))
                break;
            validate(receipt, barCode);
        }
        printer.printAllProductsOnReceipt(receipt);
        printer.printTotalSumOfReceipt(receipt);
    }

    private void validate(Receipt receipt, String barCode) {
        if(barCodeScanner.isBarCodeNull(barCode))
            lcdScreen.printIfBarCodeIsNull();
        else if(barCodeScanner.isProductInDatabase(barCode)) {
            Product scannedProduct = productRepositiory.get(barCode);
            receipt.addProduct(scannedProduct);
            lcdScreen.printNameAndPriceOfProduct(scannedProduct);
        }
        else
            lcdScreen.printIfProductNotFound();
    }
}
