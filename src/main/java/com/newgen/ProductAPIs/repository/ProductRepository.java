package com.newgen.ProductAPIs.repository;

import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends  JpaRepository<Product ,Long>
{
    List<Product> findByCategory(Category category);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetweenOrderByPriceDesc(Double lowerPrice, Double higherPrice);

   // @Query("SELECT p FROM product WHERE p.price >?1") //hql
    @Query(value = "SELECT * FROM products where product_price >?1",nativeQuery = true)
    List<Product> findAllProductByPriceGreaterThan(double price);

}
