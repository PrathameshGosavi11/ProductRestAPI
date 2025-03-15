package com.newgen.ProductAPIs.repository;

import com.newgen.ProductAPIs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends  JpaRepository<Product ,Long>
{

}
