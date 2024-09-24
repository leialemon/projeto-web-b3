package com.JJH.homebroker.service;

import com.JJH.homebroker.model.User;
import com.JJH.homebroker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FetchUserService {

    private final UserRepository userRepository;

    public FetchUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> fetchUserByCPF(String cpf){
        return userRepository.findUserByCpf(cpf);
    }

    public Optional<User> fetchUserById(long id){
        return userRepository.findById(id);
    }
}
