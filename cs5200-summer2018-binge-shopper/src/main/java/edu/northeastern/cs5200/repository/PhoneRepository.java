package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneEntity, Integer> {

    List<PhoneEntity> findByUserId(int userId);
}
