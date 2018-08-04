package edu.northeastern.cs5200.repository;


import edu.northeastern.cs5200.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

    List<AddressEntity> findByUserId(int userId);

    AddressEntity findById(int addressId);
}
