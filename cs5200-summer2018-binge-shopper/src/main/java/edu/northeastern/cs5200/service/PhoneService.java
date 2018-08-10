package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.PhoneEntity;
import edu.northeastern.cs5200.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public PhoneEntity addPhone(PhoneEntity phone){
        return phoneRepository.save(phone);
    }

    public Optional<PhoneEntity> getPhoneById(int id){
        return phoneRepository.findById(id);
    }

    public List<PhoneEntity> getUserPhone(int userId){
        return phoneRepository.findByUserId(userId);
    }

    public PhoneEntity updatePhone(PhoneEntity phone){
        PhoneEntity p = phoneRepository.findById(phone.getId()).orElseThrow(()-> new EntityNotFoundException("No phone entity with this id"));
        p.setPhone(phone.getPhone());
        return phoneRepository.save(p);
    }

    public void deletePhone(int phoneId){
        phoneRepository.deleteById(phoneId);
    }
}
