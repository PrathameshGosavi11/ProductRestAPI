package com.newgen.ProductAPIs.repository;

import com.newgen.ProductAPIs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends  JpaRepository<Product ,Long>
{
//    @Query(value="select p from Product p")
//    List<Product> findAll();
}
