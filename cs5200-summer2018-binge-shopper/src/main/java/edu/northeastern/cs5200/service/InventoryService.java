package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.InventoryEntity;
import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryEntity addInventory(UserEntity seller, ProductEntity product){
        return inventoryRepository.save(new InventoryEntity(seller, product));
    }

    public List<InventoryEntity> getAllSellerForProduct(int productId){
        return inventoryRepository.findByProductId(productId);
    }

    public InventoryEntity getInventoryBySellerAndProduct(int sellerId, int productId){
        return inventoryRepository.findBySellerIdAndProductId(sellerId, productId);
    }

    public List<InventoryEntity> getInventoryBySeller(int sellerId){
        return inventoryRepository.findBySellerId(sellerId);
    }

    public List<InventoryEntity> getInventoryByProduct(int productId){
        return inventoryRepository.findByProductId(productId);
    }

    public InventoryEntity updateInventory(InventoryEntity inventory){
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(int inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }
}
