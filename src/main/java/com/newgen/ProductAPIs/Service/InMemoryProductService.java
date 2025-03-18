package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.exception.InvalidArgumentException;
import com.newgen.ProductAPIs.exception.InvalidProductCategoryException;
import com.newgen.ProductAPIs.exception.ProductNotFound;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import com.newgen.ProductAPIs.util.Constant;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.newgen.ProductAPIs.util.Constant.INVALID_PRODUCT_CATEGORY_MESSAGE;
import static com.newgen.ProductAPIs.util.Constant.INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE;


@Service

public class InMemoryProductService implements  IProductService {

    private final Map<Long, Product> products;
    private Long id; //declare the ID automartic increment we we add the product

   // final String  INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE ="Invalid product Identifire is provided , so product not found.";


    // we initnlize the object
    public InMemoryProductService() {

        System.out.println("In Memory product services called here");
        this.products = new HashMap<>();
        this.id = 1L; //here initlize refer the current object
        initilizeProducts();
    }

    private void initilizeProducts() {

        try {


            add(new Product("Laptop", Category.ELECTRONICS, 22000.60));
            add(new Product("Mobile", Category.ELECTRONICS, 10000.60));
            add(new Product("t-Shirt", Category.CLOTHES, 200.60));
            add(new Product("bed", Category.FURNITURE, 7200.60));
            add(new Product("MOB", Category.ELECTRONICS, 11000.60));
            add(new Product("tv", Category.ELECTRONICS, 22000.60));
            add(new Product("Sunglasses", Category.CLOTHES, 500.60));
            add(new Product("glasses", Category.CLOTHES, 300.60));
        } catch (InvalidProductCategoryException e) {
            System.err.println(e.getMessage());
        }


    }

    public void add(Product product) {
        if (product.getCategory() == null) {
            throw new InvalidProductCategoryException(INVALID_PRODUCT_CATEGORY_MESSAGE);
        }
        product.setId(id);
        products.put(id, product);
        id++;
    }

    public Product getProductById(Long id) {
        System.out.println("get service method called ");
        Product product= products.get(id);
        if (product==null)
        {
            throw  new ProductNotFound(INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE);
        }
        return product;
    }

    public List<Product> getAllProduct() {
        System.out.println("get all products Method  called ");
        return new ArrayList<>(products.values());
    }

    public void  deleteProduct(Long id)  {
        System.out.println("call the deletet method on service call");
        Product product= products.remove(id);
        if (product==null)
        {
            throw  new ProductNotFound(INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE);
        }
    }

    public List<Product> searchByCategory(Category category) {
        List<Product> matchProduct = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getCategory().equals(category)) {
                matchProduct.add(product);
            }
        }
        return matchProduct;
    }


    public List<Product> searchByName(String name) {

        if(name.isEmpty())
        {
            throw  new InvalidArgumentException("Product name can not be empty");
        }
        return products.values().stream()
                .filter(product -> isNameMatching(name, product))
                .toList();

    }

    private static boolean isNameMatching(String name, Product product) {
        return product.getName().toLowerCase().contains(name.toLowerCase());
    }

    public void updateProduct(Product newProduct)  {

        Product exitstanceProduct = products.get(newProduct.getId());
        if (exitstanceProduct == null) {
            throw  new ProductNotFound(INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE);
        }
        if (exitstanceProduct != null) //if product found then only go entered update
        {
            exitstanceProduct.setName(newProduct.getName());
            exitstanceProduct.setPrice(newProduct.getPrice());
        }
        if (newProduct.getCategory() == null) {
            throw new InvalidProductCategoryException(INVALID_PRODUCT_CATEGORY_MESSAGE);
        }
    }

    public List<Product> searchProdcutByRange(Double lowerPrice, Double higherPrice) {
        List<Product> matchProduct = products.values().stream()
                .filter(product -> isPriceRangeValid(lowerPrice, higherPrice, product))
                .toList();

        List<Product> allProducts = new ArrayList<>(matchProduct);

        Collections.sort(allProducts, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        return allProducts;
    }

    private boolean isPriceRangeValid(Double lowerPrice, Double higherPrice, Product product) {

        return product.getPrice() >= lowerPrice && product.getPrice() <= higherPrice;
    }

}
