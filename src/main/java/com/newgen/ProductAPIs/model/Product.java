package com.newgen.ProductAPIs.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
    @Schema(description = "Product ID",example = "1",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long id;

    @Column(name = "Product_Name",updatable = false,nullable = false)
    @Schema(description = "Product Name",maxLength = 20) // only for documentation
    @Max(20)
    private String name;

    @Column(name="Product_Category")
    @Schema(description = "Product Category")
    private Category category;

    @Column(name="Product_Price")
    @Schema(description = "Product Price")
    private double price;

    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

}
