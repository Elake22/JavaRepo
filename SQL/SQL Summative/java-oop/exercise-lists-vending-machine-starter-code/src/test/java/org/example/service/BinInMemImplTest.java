package org.example.service;

import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinInMemImplTest {
    Bin testBin;
    Product testProductOne;
    Product testProductTwo;

    @BeforeEach
    void setUp() {
        testBin = new BinInMemImpl();
        testProductOne = new Product("Apple", .25);
        testProductTwo = new Product("Candy Bar", 1.50);
        testBin.loadOne(testProductOne);
        testBin.loadOne(testProductTwo);
    }

    @Test
    void getLength() {
        int expected = 2;
        assertEquals(expected, testBin.getLength());
    }

    @Test
    void getCurrentProductName() {
        assertEquals(testProductOne.getName(), testBin.getCurrentProductName());
    }

    @Test
    void vend() {
        int expectedLength = 1;

        Product p = testBin.vend();
        assertEquals(testProductOne, p);
        assertEquals(expectedLength, testBin.getLength());
    }

    @Test
    void loadAll() {
        testBin.dump();
        assertEquals(0, testBin.getLength());

        ArrayList<Product> products = new ArrayList<>();
        products.add(testProductTwo);
        products.add(testProductOne);

        testBin.loadAll(products);

        assertEquals(products.size(), testBin.getLength());
        assertEquals(testProductTwo, testBin.vend());
        assertEquals(testProductOne, testBin.vend());
    }

    @Test
    public void testDump() {
        int expected = 2;
        assertEquals(expected, testBin.dump());
    }
 }