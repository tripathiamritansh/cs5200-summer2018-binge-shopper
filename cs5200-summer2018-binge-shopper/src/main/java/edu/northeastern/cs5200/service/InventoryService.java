package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.inventoryEntity;
import edu.northeastern.cs5200.entity.productEntity;
import edu.northeastern.cs5200.entity.userEntity;
import edu.northeastern.cs5200.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public inventoryEntity addInventory(int sellerId, int productId){
        return inventoryRepository.save(new inventoryEntity(sellerId, productId));
    }

    public List<inventoryEntity> getAllSellerForProduct(int productId){
        return inventoryRepository.findByProductId(productId);
    }

    public inventoryEntity getInventoryBySellerAndProduct(int sellerId, int productId){
        return inventoryRepository.findBySellerIdAndProductId(sellerId, productId);
    }

    public List<inventoryEntity> getInventoryBySeller(int sellerId){
        return inventoryRepository.findBySellerId(sellerId);
    }

    public List<inventoryEntity> getInventoryByProduct(int productId){
        return inventoryRepository.findByProductId(productId);
    }

    public inventoryEntity updateInventory(inventoryEntity inventory){
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(int inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }
}
