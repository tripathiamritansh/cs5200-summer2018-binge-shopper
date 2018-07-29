package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.productEntity;
import edu.northeastern.cs5200.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public productEntity addProduct(productEntity product){
        return productRepository.save(product);
    }

    public productEntity getProductById(int id){
        return productRepository.findById(id);
    }

    public productEntity updateProduct(productEntity product){
        return productRepository.save(product);
    }

    public void deleteProduct(int productId){
        productRepository.deleteById(productId);
    }
}
