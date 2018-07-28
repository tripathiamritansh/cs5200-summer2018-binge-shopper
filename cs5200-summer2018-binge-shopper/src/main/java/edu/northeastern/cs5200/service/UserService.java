package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.userEntity;
import edu.northeastern.cs5200.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public userEntity addUser(userEntity user){
        return userRepository.save(user);
    }

    public userEntity getUserById(int userId){
        return userRepository.findById(userId);
    }

    public userEntity findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public userEntity updateUser(userEntity user){
        return userRepository.save(user);
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }
}
