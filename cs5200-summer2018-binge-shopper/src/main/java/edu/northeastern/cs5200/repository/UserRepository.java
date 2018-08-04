package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findById(int id);

    UserEntity findByUsername(String username);
}
