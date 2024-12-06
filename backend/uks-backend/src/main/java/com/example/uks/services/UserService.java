package com.example.uks.services;

import com.example.uks.model.Repository;
import com.example.uks.model.User;
import com.example.uks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Page<User> findAllUsers(Pageable page) {
        return userRepository.findAll(page);
    }


}
