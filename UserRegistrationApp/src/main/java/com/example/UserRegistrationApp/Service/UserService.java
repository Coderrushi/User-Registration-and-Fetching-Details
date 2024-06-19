package com.example.UserRegistrationApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserRegistrationApp.Entity.User;
import com.example.UserRegistrationApp.Repo.UserRepo;

@Service
public class UserService {

    @Autowired 
    private UserRepo userRepo;
    
    public User addUser(User user) {
        return userRepo.save(user);
    }
    
    public User getUser(String username) {
    	User user =userRepo.findByUsername(username);
        return user;
    }
}
