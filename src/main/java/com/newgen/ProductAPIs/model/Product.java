package com.newgen.ProductAPIs.model;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Product_Name",updatable = false,nullable = false)
    private String name;

    @Column(name="Product_Category")
    private Category category;

    @Column(name="Product_Price")
    private double price;

    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

}
