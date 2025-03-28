package com.newgen.ProductAPIs.Service;

import com.newgen.ProductAPIs.exception.InvalidArgumentException;
import com.newgen.ProductAPIs.exception.InvalidProductCategoryException;
import com.newgen.ProductAPIs.exception.ProductNotFound;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import com.newgen.ProductAPIs.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

import static com.newgen.ProductAPIs.util.Constant.INVALID_PRODUCT_CATEGORY_MESSAGE;
import static com.newgen.ProductAPIs.util.Constant.INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE;


@Service
//@Primary
@Slf4j
public class InMemoryProductService implements  IProductService {
    
    //logger
  //  private static final Logger logger=Logger.getLogger(InMemoryProductService.class.getName());

    private final Map<Long, Product> products;
    private Long productId; //declare the ID automartic increment we we add the product

   // final String  INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE ="Invalid product Identifire is provided , so product not found.";


    // we initnlize the object
    public InMemoryProductService() {

       log.info("In Memory product services called here");
        this.products = new HashMap<>();
        this.productId = 1L; //here initlize refer the current object
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
//        if(product.getName().length()>=20)
//        {
//            throw new InvalidArgumentException("You pass product name more than 20 character");
//        }
        product.setProductId(productId);
        products.put(productId, product);
        productId++;
    }

    public Product getProductById(Long productId) {
        System.out.println("get service method called ");
        Product product= products.get(productId);
        if (product==null)
        {
            throw  new ProductNotFound(INVALID_PRODUCT_IDENTIFIER_ERROR_MESSAGE);
        }
        return product;
    }

    public List<Product> getAllProduct() {
       log.info("get all products Method  called ");
        return new ArrayList<>(products.values());
    }

    public void  deleteProduct(Long productId)  {
        System.out.println("call the deletet method on service call");
        Product product= products.remove(productId);
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

        Product exitstanceProduct = products.get(newProduct.getProductId());
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
