package com.pgmanagement.service;

import com.pgmanagement.model.User;
import com.pgmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(User user) {

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        return repo.save(user);
    }
    
    public User updateUser(
            int id,
            User updatedUser){

        User existingUser =
                repo.findById(id).orElse(null);

        if(existingUser != null){

            existingUser.setName(
                    updatedUser.getName());

            existingUser.setEmail(
                    updatedUser.getEmail());

            return repo.save(existingUser);
        }

        return null;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User login(User user) {

        User existingUser = repo.findByEmail(user.getEmail());

        if(existingUser != null &&
        		   passwordEncoder.matches(
        		           user.getPassword(),
        		           existingUser.getPassword()
        		   )) {

        		    return existingUser;
        		}

        return null;
    }
}