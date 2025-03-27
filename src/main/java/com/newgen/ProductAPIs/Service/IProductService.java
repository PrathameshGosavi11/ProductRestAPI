package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;

import java.util.List;

public interface IProductService {


     void add(Product product);

     Product getProductById(Long productId);

     List<Product> getAllProduct();

     void  deleteProduct(Long id);

     List<Product> searchByCategory(Category category);

     List<Product> searchByName(String name);

     void updateProduct(Product newProduct);

     List<Product> searchProdcutByRange(Double lowerPrice, Double higherPrice);
}
