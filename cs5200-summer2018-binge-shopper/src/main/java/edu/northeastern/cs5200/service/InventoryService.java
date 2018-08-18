package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.InventoryEntity;
import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.exception.AccessDeniedException;
import edu.northeastern.cs5200.exception.InvalidPropertyException;
import edu.northeastern.cs5200.exception.NotFoundException;
import edu.northeastern.cs5200.repository.InventoryRepository;
import edu.northeastern.cs5200.repository.ProductRepository;
import edu.northeastern.cs5200.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryEntity addInventory(UserEntity seller, ProductEntity product, int price, int qty){
        UserEntity user = userRepository.findById(seller.getId());
        if(user == null)
            throw new NotFoundException("User not found!");
        if(!user.getUserType().equals("Seller"))
            throw new AccessDeniedException(new Throwable("User not a seller! Can't add pr"));
        ProductEntity pr = productRepository.findById(product.getId());
        if(pr == null) {
            productRepository.save(product);
            pr = product;
        }
        InventoryEntity inv = inventoryRepository.findBySellerIdAndProductId(user.getId(), pr.getId());
        if(inv == null)
            inv = inventoryRepository.save(new InventoryEntity(price, qty, seller, pr));
        else{
            if(qty >= 0)
                inv.setQty(inv.getQty()+qty);
            else {
                throw new InvalidPropertyException("Qty does not have a valud value");
            }
        }
        return inventoryRepository.save(inv);
    }

    public List<InventoryEntity> getAllSellerForProduct(int productId){
        return inventoryRepository.findByProductId(productId);
    }

    public List<InventoryEntity> getInventoryForSeller(int userId){
        return inventoryRepository.findBySellerId(userId);
    }

    public InventoryEntity updateInventory(InventoryEntity inventory){
        InventoryEntity inv = inventoryRepository.findById(inventory.getId());
        if(inv == null)
            throw new NotFoundException("Inventory not found for seller");
        inv.setPrice(inventory.getPrice());
        inv.setQty(inventory.getQty());
        return inventoryRepository.save(inv);
    }

    public InventoryEntity updateProductQtyForSeller(int sellerId, int productId, int qty, String action){
        InventoryEntity inv = inventoryRepository.findBySellerIdAndProductId(sellerId, productId);
        if(action.equals("add")){
            inv.setQty(inv.getQty() + qty);
        }
        else if(action.equals("subtract") && (inv.getQty() > 0) && (inv.getQty()- qty >= 0)) {
            inv.setQty(inv.getQty() - qty);
        }
        else{
            throw new NotFoundException("Action does not match add or delete products");
        }
        return inventoryRepository.save(inv);
    }

    public void deleteInventory(int inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }
}
