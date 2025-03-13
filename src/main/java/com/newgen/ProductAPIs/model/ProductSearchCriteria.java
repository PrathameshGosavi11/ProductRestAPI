package com.newgen.ProductAPIs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ProductSearchCriteria
{
    private String name;
    private Category category;
    private double lowerPrice;
    private double higherPrice;


}
