package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DatabaseProductService implements IProductService
{

    public  DatabaseProductService()
    {
        System.out.println("Product service database servicec call here ");
    }

    @Override
    public void add(Product product) {
        
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return List.of();
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<Product> searchByCategory(Category category) {
        return List.of();
    }

    @Override
    public List<Product> searchByName(String name) {
        return List.of();
    }

    @Override
    public void updateProduct(Product newProduct) {

    }

    @Override
    public List<Product> searchProdcutByRange(Double lowerPrice, Double higherPrice) {
        return List.of();
    }
}
