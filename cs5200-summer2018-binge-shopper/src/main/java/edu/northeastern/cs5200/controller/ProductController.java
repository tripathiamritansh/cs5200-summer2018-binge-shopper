package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.service.InventoryService;
import edu.northeastern.cs5200.service.ProductService;
import edu.northeastern.cs5200.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("add")
    public ProductEntity addProduct(@RequestBody ProductEntity product){
        return productService.addProduct(product);
    }

    @GetMapping("{productId}/get")
    public ProductEntity getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("getByName")
    public ProductEntity getProductByName(String productName){
        return productService.getProductByName(productName);
    }

    @PostMapping("update")
    public ProductEntity updateProduct(@RequestBody ProductEntity product) throws NotFoundException {
        return productService.updateProduct(product);
    }

    @PostMapping("delete")
    public void deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
    }
}
