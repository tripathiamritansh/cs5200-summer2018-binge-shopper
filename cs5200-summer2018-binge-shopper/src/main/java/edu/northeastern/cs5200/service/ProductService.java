package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.repository.ProductRepository;
import javassist.NotFoundException;
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

    public ProductEntity getProductByName(String productName){
        return productRepository.findByName(productName);
    }

    public ProductEntity updateProduct(ProductEntity product) throws NotFoundException {
        ProductEntity p = productRepository.findById(product.getId());
        if(p == null)
            throw new NotFoundException("Product not found!");
        p.setCategory(product.getCategory());
        p.setImage_url(product.getImage_url());
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setQty(product.getQty());
        return productRepository.save(p);
    }

    public void deleteProduct(int productId){
        productRepository.deleteById(productId);
    }
}
