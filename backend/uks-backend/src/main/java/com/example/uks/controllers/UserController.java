package com.example.uks.controllers;

import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.dto.user.UserDTO;
import com.example.uks.dto.util.PagedResponse;
import com.example.uks.model.Repository;
import com.example.uks.model.User;
import com.example.uks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user));
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }


    // /api/users?page=0&size=5&sort=firstName,DESC
    @GetMapping
    public ResponseEntity<PagedResponse<UserDTO>> getUserPage(Pageable page) {
        Page<User> users = userService.findAllUsers(page);

        List<UserDTO> usersContent = new ArrayList<>();
        for (User user : users) {
            usersContent.add(new UserDTO(user));
        }

        PagedResponse<UserDTO> response = new PagedResponse<>(
                usersContent,
                users.getTotalPages(),
                users.getTotalElements()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // /api/users/search?page=0&size=10&sort=firstName,DESC&firstName=Fiona&lastName=Gray&username=fionagray&role=USER&userBadge=SPONSORED_OSS
    @GetMapping(value = "/search")
    public ResponseEntity<PagedResponse<UserDTO>> filterUsers(@RequestParam(required = false) String firstName,
                                                                           @RequestParam(required = false) String lastName,
                                                                           @RequestParam(required = false) String username,
                                                                           @RequestParam(required = false) String role,
                                                                           @RequestParam(required = false) String userBadge,
                                                                           Pageable page)
    {
        Page<User> users = userService.findUsersByField(firstName, lastName, username, role, userBadge, page);

        List<UserDTO> userContent = users.stream()
                .map(UserDTO::new)
                .toList();

        PagedResponse<UserDTO> response = new PagedResponse<>(
                userContent,
                users.getTotalPages(),
                users.getTotalElements()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
