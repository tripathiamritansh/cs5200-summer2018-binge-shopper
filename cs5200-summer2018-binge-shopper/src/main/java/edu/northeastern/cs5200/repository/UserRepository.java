package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findById(int id);

    List<UserEntity> findByUserType(String type);

    UserEntity findByUsername(String username);
}
