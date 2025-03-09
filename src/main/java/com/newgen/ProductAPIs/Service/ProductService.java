package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        add(new Product( "Laptop", Category.ELECTRONICS, 22000.60));
        add(new Product( "t-Shirt", Category.CLOTHES, 200.60));
        add(new Product( "bed", Category.FURNITURE, 7200.60));
        add(new Product( "MOB", Category.ELECTRONICS, 11000.60));
        add(new Product( "tv", Category.ELECTRONICS, 22000.60));
    }

    public void add(Product product)
    {
        product.setId(id);
        products.put(id,product);
        id++;
    }

    public   Product getProductById(Long id)
    {
        System.out.println("get service method called ");
        return products.get(id);
    }

    public List<Product> getAllProduct()
    {
        System.out.println("get all products Method  called ");
        return new ArrayList<>(products.values());
    }

    public  boolean deleteProduct(Long id)
    {
        System.out.println("call the deletet method on service call");
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

//    public List<Product> searchByName(String name)
//    {
//        List<Product> matchProduct=new ArrayList<>();
//
//        for(Product product:products.values())
//        {
//            if(product.getName().equals(name))
//            {
//                matchProduct.add(product);
//            }
//        }
//        return  matchProduct;
//    }
    public  List<Product> searchByName(String name)
    {
       return products.values().stream()
                .filter(product -> product.getName().equals(name))
                .collect(Collectors.toList());

    }

    public  boolean updateProduct(Product newProduct) {

       Product exitstanceProduct= products.get(newProduct.getId());
       if(exitstanceProduct!=null) //if product found then only go entered update
        {
            exitstanceProduct.setName(newProduct.getName());
            exitstanceProduct.setPrice(newProduct.getPrice());
            return true;
        }
       return false; //if product is null then not entered if and return false.
    }

}
