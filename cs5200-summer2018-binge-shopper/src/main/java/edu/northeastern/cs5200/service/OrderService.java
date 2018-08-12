//package edu.northeastern.cs5200.service;
//
//import edu.northeastern.cs5200.entity.OrderEntity;
//import edu.northeastern.cs5200.entity.UserEntity;
//import edu.northeastern.cs5200.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    public OrderEntity addOrder(UserEntity buyer){
//        return orderRepository.save(new OrderEntity(buyer));
//    }
//
//    public List<OrderEntity> getOrderByBuyer(int buyerId){
//        return orderRepository.findByBuyerId(buyerId);
//    }
//
//    public OrderEntity getOrderById(int orderId){
//        return orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("No order entity with this id"));
//    }
//
//    public OrderEntity updateOrder(OrderEntity order){
//        return orderRepository.save(order);
//    }
//
//    public void deleteOrder(int orderId){
//        orderRepository.deleteById(orderId);
//    }
//}


//package edu.northeastern.cs5200.service;
//
//import edu.northeastern.cs5200.entity.ProductEntity;
//import edu.northeastern.cs5200.entity.UserEntity;
//import edu.northeastern.cs5200.entity.WishlistEntity;
//import edu.northeastern.cs5200.exception.NotFoundException;
//import edu.northeastern.cs5200.repository.ProductRepository;
//import edu.northeastern.cs5200.repository.UserRepository;
//import edu.northeastern.cs5200.repository.WishlistRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class WishlistService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private WishlistRepository wishlistRepository;
//
//    public void addProductInWishlist(int userId, int qty, ProductEntity product) throws NotFoundException{
//        UserEntity user = userRepository.findById(userId);
//        if(user == null)
//            throw new NotFoundException("User not found!");
//        int index = 0;
//        for(int p: products){
//            index = products.indexOf(p);
//            WishlistEntity wishlist = new WishlistEntity();
//            wishlist.setUser(user);
//            wishlist.setProduct(productRepository.findById(p));
//            wishlist.setQty(quantities.get(index));
//            wishlistRepository.save(wishlist);
//        }
//    }
//
//    public List<WishlistEntity> getWishlistProductsByUser(int userId){
//        return wishlistRepository.findByUserId(userId);
//    }
//
//    public void updateProductsInWishlist(int userId, List<Integer> products, List<Integer> quantities) throws NotFoundException{
//        UserEntity user = userRepository.findById(userId);
//        if(user == null)
//            throw new NotFoundException("User not found!");
//        int index;
//        for(int productId: products){
//            index = products.indexOf(productId);
//            WishlistEntity wishlist = wishlistRepository.findByUserIdAndProductId(userId, productId);
//            if(wishlist != null){
//                wishlist.setQty(quantities.get(index));
//                wishlistRepository.save(wishlist);
//            }
//            else {
//                wishlist = new WishlistEntity();
//                wishlist.setUser(user);
//                wishlist.setProduct(productRepository.findById(productId));
//                wishlist.setQty(quantities.get(index));
//                wishlistRepository.save(wishlist);
//            }
//        }
//    }
//
//    public void deleteProductsFromWishlist(int userId, List<ProductEntity> products){
//        UserEntity user = userRepository.findById(userId);
//        if(user == null)
//            throw new NotFoundException("User not found!");
//        for(ProductEntity product: products){
//            WishlistEntity wishlist = wishlistRepository.findByUserIdAndProductId(userId, product.getId());
//            if(wishlist != null)
//                wishlistRepository.delete(wishlist);
//        }
//    }
//}

