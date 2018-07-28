package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.repository.UserRepository;
import edu.northeastern.cs5200.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
}
