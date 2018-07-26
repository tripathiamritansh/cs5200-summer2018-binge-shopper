package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.transactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<transactionEntity, Integer> {

    List<transactionEntity> findByProductId(int productId);

    List<transactionEntity> findBySellerId(int sellerId);

    List<transactionEntity> findByOrderId(int orderId);

    transactionEntity findByOrderIdAndSellerIdAndProductId(int orderId, int sellerId, int productId);

}
