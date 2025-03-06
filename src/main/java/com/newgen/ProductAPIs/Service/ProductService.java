package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class ProductService
{
    private final Map<Long, Product> products;

    private  Long id; //declare the ID automartic increment we we add the product
    // we initnlize the object
    public  ProductService()
    {
        this.products=new HashMap<>();
        this.id=1L; //here initlize refer the current object
        initilizeProducts();
    }

    private void initilizeProducts() {

        add(new Product(1, "Laptop", Category.ELECTRONICS, 22000.60));
        add(new Product(2, "t-Shirt", Category.CLOTHES, 200.60));
        add(new Product(3, "bed", Category.FURNITURE, 7200.60));
        add(new Product(4, "MOB", Category.ELECTRONICS, 11000.60));
        add(new Product(5, "tv", Category.ELECTRONICS, 22000.60));
    }

    public void add(Product product)
    {
        products.put(id,product);
        id++;
    }

    public  Product getProductById(Long id)
    {
        return products.get(id);
    }

    public List<Product> getAllProduct()
    {
        System.out.println("get all products Method  called ");
        return new ArrayList<>(products.values());
    }

    public  boolean deleteProduct(Long id)
    {
        return  products.remove(id) !=null ;
    }

    public List<Product> searchByCategory(Category category)
    {
        List<Product> matchProduct=new ArrayList<>();

        for(Product product:products.values())
        {
            if(product.getCategory().equals(category))
            {
                matchProduct.add(product);
            }
        }
        return  matchProduct;
    }
}
