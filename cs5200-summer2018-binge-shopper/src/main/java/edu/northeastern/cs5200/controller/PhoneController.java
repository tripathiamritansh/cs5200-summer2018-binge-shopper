package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.PhoneEntity;
import edu.northeastern.cs5200.service.PhoneService;
import edu.northeastern.cs5200.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("api/phone")
public class PhoneController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @PostMapping("user/{userId}/add")
    public PhoneEntity addPhone(@PathVariable int userId, @RequestBody PhoneEntity phone){
        phone.setUser(userService.getUserById(userId));
        return phoneService.addPhone(phone);
    }

    @GetMapping("user/{userId}/get")
    public List<PhoneEntity> getUserPhone (@PathVariable int userId){
        return phoneService.getUserPhone(userId);
    }

    @PutMapping("user/update")
    public PhoneEntity updatePhone(@RequestBody PhoneEntity phone, HttpSession session){
        return phoneService.updatePhone(phone);
    }

    @DeleteMapping("user/{phoneId}/delete")
    public void deletePhone(@PathVariable int phoneId){
        phoneService.deletePhone(phoneId);
    }
}
