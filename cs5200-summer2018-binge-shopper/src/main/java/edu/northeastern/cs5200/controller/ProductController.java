package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

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

    @GetMapping("{productName}/getByName")
    public ProductEntity getProductByName(@PathVariable String productName){
        return productService.getProductByName(productName);
    }

    @PutMapping("update")
    public ProductEntity updateProduct(@RequestBody ProductEntity product) throws NotFoundException {
        return productService.updateProduct(product);
    }

    @DeleteMapping("delete")
    public void deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
    }
}
