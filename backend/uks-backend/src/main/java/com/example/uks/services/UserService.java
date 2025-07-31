package com.example.uks.services;

import com.example.uks.dto.user.BadgeDTO;
import com.example.uks.dto.user.UpdateUserDTO;
import com.example.uks.enumeration.RoleEnum;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.exceptions.AttributeNullException;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.User;
import com.example.uks.repositories.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

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
                predicates.add(criteriaBuilder.equal(root.get("role"), RoleEnum.valueOf(role)));
            }

            if (userBadge != null) {
                System.out.println("Tatjana");
                predicates.add(criteriaBuilder.equal(root.get("userBadge"), UserBadge.valueOf(userBadge)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User addBadge(Integer id, BadgeDTO badgeDTO){

        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            return null;
        }

        user.setUserBadge(badgeDTO.getUserBadge());
        return userRepository.save(user);

    }

    public User updateUser(Integer id, UpdateUserDTO updateUserDTO){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new UserNotFound("User with ID " + id + " does not exist");
        }

        if(updateUserDTO.getFirstName() != null){
            user.setFirstName(updateUserDTO.getFirstName());
        }else{
            throw new AttributeNullException("First name could not have value null.");
        }

        if(updateUserDTO.getLastName() != null){
            user.setLastName(updateUserDTO.getLastName());
        }else{
            throw new AttributeNullException("Last name could not have value null.");
        }

        if(updateUserDTO.getUsername() != null){
            if(userRepository.findByUsername(updateUserDTO.getUsername()) == null){
                user.setUsername(updateUserDTO.getUsername());
            }else{
                throw new AttributeNullException("Username with name " + updateUserDTO.getUsername() + " already exists!");
            }
        }else{
            throw new AttributeNullException("Username could not have value null.");
        }

        if(updateUserDTO.getEmail() != null){
            if(userRepository.findByEmail(updateUserDTO.getEmail()) == null){
                user.setEmail(updateUserDTO.getEmail());
            }else{
                throw new AttributeNullException("Email with name " + updateUserDTO.getEmail() + " already exists!");
            }
        }else{
            throw new AttributeNullException("Email could not have value null.");
        }

        return userRepository.save(user);
    }

public void disableUser(Integer id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setEnabled(false);
            userRepository.save(user);
        }
    }
    
    public User save(User user){
        return userRepository.save(user);
    }
    
    public List<User> getUsersByBadge(UserBadge badge) {
        return userRepository.findByUserBadge(badge);
    }

}
