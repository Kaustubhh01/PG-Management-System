package com.pgmanagement.controller;

import com.pgmanagement.model.User;
import com.pgmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgmanagement.dto.LoginResponse;
import com.pgmanagement.security.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }
    
    @PutMapping("/update/{id}")
    public User updateUser(
            @PathVariable int id,
            @RequestBody User user){

        return service.updateUser(id, user);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody User user) {

        User loggedInUser =
                service.login(user);

        if(loggedInUser == null){
            return null;
        }

        String token =
                jwtUtil.generateToken(
                        loggedInUser.getEmail(),
                        loggedInUser.getRole()
                );

        return new LoginResponse(
                token,
                loggedInUser.getId(),
                loggedInUser.getName(),
                loggedInUser.getEmail(),
                loggedInUser.getRole()
        );
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return service.getAllUsers();
    }
}