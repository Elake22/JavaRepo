package org.example.service;

import org.example.model.Product;

import java.util.Collection;

public class BinInMemImpl implements Bin {
    public int getLength() {
        throw new RuntimeException("Not implemented.");
    }

    public String getCurrentProductName() {
        throw new RuntimeException("Not implemented.");
    }

    public Product vend() {
        throw new RuntimeException("Not implemented.");
    }

    public void loadOne(Product product) {
        throw new RuntimeException("Not implemented.");
    }

    public void loadAll(Collection<Product> products) {
        throw new RuntimeException("Not implemented.");
    }

    public int dump() {
        throw new RuntimeException("Not implemented.");
    }
}
