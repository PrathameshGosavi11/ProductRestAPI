package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.controller.ProductController;
import com.newgen.ProductAPIs.exception.InvalidArgumentException;
import com.newgen.ProductAPIs.exception.InvalidProductCategoryException;
import com.newgen.ProductAPIs.exception.ProductNotFound;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import com.newgen.ProductAPIs.repository.ProductRepository;
import com.newgen.ProductAPIs.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service("databaseSerice")
@Slf4j
@Primary
public class DatabaseProductService implements IProductService
{
    //logger object
    //private static final Logger logger =Logger.getLogger(DatabaseProductService.class.getName());

    private  ProductRepository productRepository;

    @Autowired
    public  DatabaseProductService(ProductRepository productRepository)
    {
//        System.out.println("Product service database servicec call here =>"+productRepository);
        log.info("DataBase Product service=> {}",productRepository);
        this.productRepository=productRepository;
    }

    @Override
    public void add(Product product) {
        //add the product
        // how call the save method here
        if (product.getCategory() == null) {
            throw new InvalidProductCategoryException(Constant.INVALID_PRODUCT_CATEGORY_MESSAGE);
        }
//        if(product.getName().length() >=20)
//        {
//            throw new InvalidArgumentException("you pass name more than 20 character");
//        }
        productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> product= productRepository.findById(productId);
        if(product.isPresent())
        {
            return product.get();
        }
        else
        {
            throw new ProductNotFound(Constant.INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Product> getAllProduct() {
       return productRepository.findAll();

    }

    @Override
    public void deleteProduct(Long productId) {

       Optional<Product> product= productRepository.findById(productId);
       if(product.isPresent())
       {
           productRepository.deleteById(productId);
       }
       else
       {
           throw  new ProductNotFound(Constant.INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE);
       }
    }

    @Override
    public List<Product> searchByCategory(Category category) {

        if(category==null)
        {
            throw new InvalidProductCategoryException(Constant.INVALID_PRODUCT_CATEGORY_MESSAGE);
        }
       return productRepository.findByCategory(category);

    }

    @Override
    public List<Product> searchByName(String name) {

        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void updateProduct(Product newProduct) {

        if (newProduct.getCategory() == null) {
            throw new InvalidProductCategoryException(Constant.INVALID_PRODUCT_CATEGORY_MESSAGE);
        }
        try
        {
            productRepository.save(newProduct);
        } catch (Exception e) {
            log.error(" Invalid Product id => {}",e);
            throw new ProductNotFound(Constant.INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Product> searchProdcutByRange(Double lowerPrice, Double higherPrice) {
        return  productRepository.findByPriceBetweenOrderByPriceDesc(lowerPrice,higherPrice);
    }
}
