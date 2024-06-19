package com.example.UserRegistrationApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserRegistrationApp.Config.PasswordEncoder;
import com.example.UserRegistrationApp.Entity.User;
import com.example.UserRegistrationApp.Service.UserService;

@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody User newUser) {
		User oldUser = userService.getUser(newUser.getUsername());
		if (oldUser == null) {
			String encodedPassword = passwordEncoder.encode(newUser.getPassword());
			newUser.setPassword(encodedPassword);
			userService.addUser(newUser);
			return ResponseEntity.status(HttpStatus.OK).body("User Registered successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already exists");
		}
	}

	@GetMapping("/fetch/{username}")
	public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
		try {
			User user = userService.getUser(username);
			if (user == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User Found");
			} else {
				return ResponseEntity.ok(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user details");
		}
	}
}
