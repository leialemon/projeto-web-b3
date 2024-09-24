package com.JJH.homebroker.service;

import com.JJH.homebroker.model.User;
import com.JJH.homebroker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
}
