package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity addUser(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity getUserById(int userId){
        return userRepository.findById(userId);
    }

    public UserEntity findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserEntity updateUser(UserEntity user){
        return userRepository.save(user);
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }
}
