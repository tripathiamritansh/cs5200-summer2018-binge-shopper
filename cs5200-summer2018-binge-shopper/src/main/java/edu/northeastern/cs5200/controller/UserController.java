package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public UserEntity login(String username, String password, HttpSession session) throws Exception{

        UserEntity user = userService.findUserByUsername(username);

        if(user != null  && BCrypt.checkpw(password, user.getPassword())){

            if(user.getUserType().equals("Buyer") || user.getUserType().equals("Seller") && !user.getApproved()){
                throw new Exception("User requires admin approval to login!");
            }
            session.setAttribute("user_session", user);
            session.setMaxInactiveInterval(300);
            return user;
        }
        else
            throw new Exception("Invalid username and password| Try again !");

    }

    @PostMapping("register")
    public UserEntity register(@RequestBody UserEntity u, HttpSession session) throws Exception{

        if(userService.findUserByUsername(u.getUsername()) != null){
            throw new Exception("User already registered| Login directly!");
        }

        u.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
        UserEntity user = userService.addUser(u);

        if(user.getUserType().equals("Buyer") || user.getUserType().equals("Seller")){
            throw new Exception("User registered successfully| Needs to be approved by Admin!");
        }

        session.setAttribute("user_session", user);
        session.setMaxInactiveInterval(300);
        return user;
    }

    @PostMapping("update")
    public UserEntity update(@RequestBody UserEntity user, HttpSession session) throws Exception{

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        session.setAttribute("user_session", user);
        session.setMaxInactiveInterval(300);
        return userService.updateUser(user);
    }

    @PostMapping("logout")
    public ResponseEntity logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity("{}", HttpStatus.OK);
    }
}
