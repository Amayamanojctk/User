package com.ty.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.spring.dao.UserRepository;
import com.ty.spring.entity.User;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
       
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");   // Check if email is unique
        }
        

       
        return userRepository.save(user);   // Save user to database
    }
    public User validateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

}
