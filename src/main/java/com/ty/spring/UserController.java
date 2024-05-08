package com.ty.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.spring.entity.User;
import com.ty.spring.service.UserService;


@RestController
public class UserController {
	 @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	    public ResponseEntity<String> registerUser(@RequestBody User user) {
	        try {
	            userService.registerUser(user);
	            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
	    @PostMapping("/validate")
	    public ResponseEntity<User> validateUser(@RequestParam String email, @RequestParam String password) {
	        try {
	            User user = userService.validateUser(email, password);
	            return ResponseEntity.ok(user);
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    }

	    @GetMapping("/users")
	    public Iterable<User> getAllUsers() {
	        return userService.getAllUsers();
	    }

	    @DeleteMapping("/delete/{email}")
	    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
	        try {
	            userService.deleteUserByEmail(email);
	            return ResponseEntity.ok("User deleted successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
}
