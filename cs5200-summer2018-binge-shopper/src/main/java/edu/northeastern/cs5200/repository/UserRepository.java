package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.userEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<userEntity, Integer> {

    userEntity findById(int id);
}
