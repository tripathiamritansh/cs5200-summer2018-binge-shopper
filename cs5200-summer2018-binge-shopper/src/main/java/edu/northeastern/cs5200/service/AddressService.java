package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.addressEntity;
import edu.northeastern.cs5200.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public addressEntity addAddress(addressEntity address){
        return addressRepository.save(address);
    }

    public addressEntity getAddressById(int id){
        return addressRepository.findById(id);
    }

    public List<addressEntity> getUserAddress(int userId){
        return addressRepository.findByUserId(userId);
    }

    public addressEntity updateAddress(addressEntity address){
        return addressRepository.save(address);
    }

    void deleteAddress(addressEntity address){
        addressRepository.delete(address);
    }
}
