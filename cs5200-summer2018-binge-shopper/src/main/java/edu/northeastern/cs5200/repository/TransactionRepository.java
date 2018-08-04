package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByProductId(int productId);

    List<TransactionEntity> findBySellerId(int sellerId);

    List<TransactionEntity> findByOrderId(int orderId);

    TransactionEntity findByOrderIdAndSellerIdAndProductId(int orderId, int sellerId, int productId);

}
