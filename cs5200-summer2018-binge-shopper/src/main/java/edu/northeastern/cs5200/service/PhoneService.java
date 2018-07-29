package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.phoneEntity;
import edu.northeastern.cs5200.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public phoneEntity addPhone(phoneEntity phone){
        return phoneRepository.save(phone);
    }

    public Optional<phoneEntity> getPhoneById(int id){
        return phoneRepository.findById(id);
    }

    public phoneEntity updatePhone(phoneEntity phone){
        return phoneRepository.save(phone);
    }

    public void deletePhone(int phoneId){
        phoneRepository.deleteById(phoneId);
    }
}
