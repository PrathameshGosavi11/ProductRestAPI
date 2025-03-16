package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import com.newgen.ProductAPIs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DatabaseProductService implements IProductService
{


    private ProductRepository productRepository;

    @Autowired
    public  DatabaseProductService(ProductRepository productRepository)
    {
        System.out.println("database service callaed ");
        this.productRepository=productRepository;
    }

    public  DatabaseProductService()
    {
        System.out.println("Product service database servicec call here ");
    }

    @Override
    public void add(Product product) {
        //add the product
        // how call the save method here
        productRepository.save(product);
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
