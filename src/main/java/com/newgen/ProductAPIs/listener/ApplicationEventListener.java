package com.newgen.ProductAPIs.listener;

import com.newgen.ProductAPIs.Service.IProductService;
import com.newgen.ProductAPIs.exception.InvalidProductCategoryException;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    @Autowired
    private IProductService productService;

    @EventListener(ApplicationReadyEvent.class)
    public void handleEvent(ApplicationEvent event)
    {
        System.out.println("recive application event:"+event);
        initilizeProducts();
    }

    private  void initilizeProducts()
    {
        try {


            productService.add(new Product("Laptop", Category.ELECTRONICS, 22000.60));
            productService.add(new Product("Mobile", Category.ELECTRONICS, 10000.60));
            productService.add(new Product("t-Shirt", Category.CLOTHES, 200.60));
            productService.add(new Product("bed", Category.FURNITURE, 7200.60));
            productService.add(new Product("MOB", Category.ELECTRONICS, 11000.60));
            productService.add(new Product("tv", Category.ELECTRONICS, 22000.60));
            productService.add(new Product("Sunglasses", Category.CLOTHES, 500.60));
            productService.add(new Product("glasses", Category.CLOTHES, 300.60));
        } catch (InvalidProductCategoryException e) {
            System.err.println(e.getMessage());
        }

    }
}
