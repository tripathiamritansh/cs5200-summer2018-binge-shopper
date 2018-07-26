package edu.northeastern.cs5200.repository;


import edu.northeastern.cs5200.entity.addressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<addressEntity, Integer> {

    List<addressEntity> findByUserId(int userId);

    addressEntity findById(int addressId);
}
