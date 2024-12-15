package com.example.uks.controllers;

import com.example.uks.dto.auth.JwtAuthenticationRequest;
import com.example.uks.dto.auth.UserRequest;
import com.example.uks.dto.auth.UserTokenState;
import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.dto.user.UserDTO;
import com.example.uks.exceptions.ResourceConflictException;
import com.example.uks.model.User;
import com.example.uks.services.UserService;
import com.example.uks.util.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) {

        try{
            // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
            // AuthenticationException
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
            // kontekst
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Kreiraj token za tog korisnika
            User user = (User) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user);
            int expiresIn = tokenUtils.getExpiredIn();

            Map<String, Object> response = new HashMap<>();
            response.put("message", null);
            response.put("data", new UserTokenState(jwt, expiresIn));
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (BadCredentialsException exception){
            Map<String, Object> nesto = new HashMap<>();
            nesto.put("message", "User with this credentials does not exist.");
            nesto.put("data", null);
            return new ResponseEntity<>(nesto, HttpStatus.UNAUTHORIZED);
            //return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }

    }


    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserRequest userRequest) {
        User existUser = this.userService.findByUsername(userRequest.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(existUser.getId(), "User already exists");
        }

        User savedUser =  this.userService.save(userRequest);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(), userRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();

        Map<String, Object> response = new HashMap<>();
        response.put("message", null);
        response.put("data", new UserTokenState(jwt, expiresIn));
        response.put("user_data", new UserDTO(savedUser));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout() {
        SecurityContextHolder.clearContext();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully logged out.");
        response.put("data", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}