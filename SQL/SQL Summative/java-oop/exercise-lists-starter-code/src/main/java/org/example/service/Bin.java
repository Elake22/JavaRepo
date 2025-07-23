package org.example.service;

import org.example.model.Product;

import java.util.Collection;

public interface Bin {
    int getLength();
    String getCurrentProductName();
    Product vend();
    void loadOne(Product product);
    void loadAll(Collection<Product> products);
    int dump();
}
