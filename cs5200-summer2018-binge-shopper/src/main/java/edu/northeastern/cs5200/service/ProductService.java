package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity addProduct(ProductEntity product){
        return productRepository.save(product);
    }

    public ProductEntity getProductById(int id){
        return productRepository.findById(id);
    }

    public ProductEntity updateProduct(ProductEntity product){
        return productRepository.save(product);
    }

    public void deleteProduct(int productId){
        productRepository.deleteById(productId);
    }
}
