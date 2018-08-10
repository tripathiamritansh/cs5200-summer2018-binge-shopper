package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.AddressEntity;
import edu.northeastern.cs5200.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressEntity addAddress(AddressEntity address){
        return addressRepository.save(address);
    }

    public AddressEntity getAddressById(int id){
        return addressRepository.findById(id);
    }

    public List<AddressEntity> getUserAddress(int userId){
        return addressRepository.findByUserId(userId);
    }

    public AddressEntity updateAddress(AddressEntity address){
        AddressEntity a = addressRepository.findById(address.getId());
        a.setStreet1(address.getStreet1());
        a.setStreet2(address.getStreet2());
        a.setCity(address.getCity());
        a.setState(address.getState());
        a.setZip(address.getZip());
        return addressRepository.save(a);
    }

    public void deleteAddress(AddressEntity address){
        addressRepository.delete(address);
    }
}
