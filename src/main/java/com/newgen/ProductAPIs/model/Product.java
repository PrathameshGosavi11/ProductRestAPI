package com.newgen.ProductAPIs.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Product
{
    private int id;
    private  String name;
    private Category category;
    private  double price;
}
