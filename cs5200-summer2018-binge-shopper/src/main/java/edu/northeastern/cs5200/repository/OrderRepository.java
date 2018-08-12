package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    OrderEntity findById(int id);

    List<OrderEntity> findByBuyerId(int buyerId);
}
