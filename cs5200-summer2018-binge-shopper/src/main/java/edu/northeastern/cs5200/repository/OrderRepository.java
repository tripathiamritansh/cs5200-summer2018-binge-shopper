package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.orderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<orderEntity, Integer> {

    List<orderEntity> findByBuyerId(int buyerId);
}
