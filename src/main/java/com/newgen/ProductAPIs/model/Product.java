package com.newgen.ProductAPIs.model;

import lombok.*;


@Setter
@Getter
@ToString
public class Product {
    @Setter
    private long id;
    private String name;
    private Category category;
    private double price;

    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

}
