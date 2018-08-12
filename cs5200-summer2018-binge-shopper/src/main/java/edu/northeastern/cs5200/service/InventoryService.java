package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.InventoryEntity;
import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.exception.NotFoundException;
import edu.northeastern.cs5200.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryEntity addInventory(UserEntity seller, ProductEntity product, int price, int qty){
        return inventoryRepository.save(new InventoryEntity(price, qty, seller, product));
    }

    public List<InventoryEntity> getAllSellerForProduct(int productId){
        return inventoryRepository.findByProductId(productId);
    }

    public InventoryEntity updateInventory(int userId, InventoryEntity inventory){
        InventoryEntity inv = inventoryRepository.findBySellerId(userId);
        if(inv == null)
            throw new NotFoundException("Inventory not found for seller");
        inv.setPrice(inventory.getPrice());
        inv.setQty(inventory.getQty());
        return inventoryRepository.save(inv);
    }

    public void deleteInventory(int inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }
}
