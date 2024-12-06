package com.example.uks.services;

import com.example.uks.enumeration.Category;
import com.example.uks.enumeration.Role;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.model.Repository;
import com.example.uks.model.User;
import com.example.uks.repositories.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    public Page<User> findUsersByField(String firstName, String lastName, String username, String role, String userBadge, Pageable pageable) {
        return userRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (firstName != null) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
            }

            if (lastName != null) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
            }

            if (username != null) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }

            if (role != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), Role.valueOf(role)));
            }

            if (userBadge != null) {
                System.out.println("Tatjana");
                predicates.add(criteriaBuilder.equal(root.get("userBadge"), UserBadge.valueOf(userBadge)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
}
