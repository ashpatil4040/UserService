package com.lcwd.user.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
       
        //generate unique user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        
        return userRepository.findAll();

    }

    @Override
    public User getUser(String userId) {
        
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
    }

    @Override
    public User deleteUser(String userId) {
       
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        userRepository.delete(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User user1= userRepository.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !! : " + user.getUserId()));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());
        return userRepository.save(user1);

    }



}
