package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.AddressEntity;
import edu.northeastern.cs5200.service.AddressService;
import edu.northeastern.cs5200.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping("user/{userId}/add")
    public AddressEntity addAddress(@PathVariable int userId, @RequestBody AddressEntity address){
        address.setUser(userService.getUserById(userId));
        return addressService.addAddress(address);
    }

    @GetMapping("user/{userId}/get")
    public List<AddressEntity> getUserAddress (@PathVariable int userId){
        return addressService.getUserAddress(userId);
    }

    @PutMapping("user/update")
    public AddressEntity updateAddress(@RequestBody AddressEntity address, HttpSession session){
        return addressService.updateAddress(address);
    }

    @DeleteMapping("user/{addressId}/delete")
    public void deleteAddress(@PathVariable int addressId){
        AddressEntity address = addressService.getAddressById(addressId);
        addressService.deleteAddress(address);
    }
}
