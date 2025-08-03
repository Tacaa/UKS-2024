package com.example.uks.controllers;

import com.example.uks.dto.auth.JwtAuthenticationRequest;
import com.example.uks.dto.auth.SuperAdminLoginRequest;
import com.example.uks.dto.auth.UserRequest;
import com.example.uks.dto.auth.UserTokenState;
import com.example.uks.dto.user.UserDTO;
import com.example.uks.enumeration.RoleEnum;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.model.Role;
import com.example.uks.services.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.uks.exceptions.ResourceConflictException;
import com.example.uks.model.User;
import com.example.uks.services.UserService;
import com.example.uks.util.security.TokenUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    //metoda koja sluzi za logovanje, izdvojena kako se ne bi duplirao kod i u registraciji
    private UserTokenState login(JwtAuthenticationRequest authenticationRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();

        return new UserTokenState(jwt, expiresIn);
    }


    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
        UserTokenState token = this.login(authenticationRequest);
        return ResponseEntity.ok(token);
    }

    private String readSuperAdmin() {
        try {
            return Files.readAllLines(Paths.get("/app/logs/uks-super-admin.txt")).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/super-admin-login")
    public ResponseEntity<UserTokenState> superAdminLogin(
            @RequestBody SuperAdminLoginRequest superAdminLoginRequest, HttpServletResponse response) {

        //provjeriti da li postoji
        String generatedPassword = this.readSuperAdmin();
        System.out.println("HALO");
        System.out.println(generatedPassword);

        if(generatedPassword == null){
            return ResponseEntity.notFound().build();
        }

        System.out.println("pisi");
        System.out.println(generatedPassword);
        System.out.println(superAdminLoginRequest.getPassword());
        System.out.println(superAdminLoginRequest.getNewPassword());

        if(!generatedPassword.equals(superAdminLoginRequest.getPassword())){
            System.out.println("UPADAM");
            return ResponseEntity.notFound().build();
        }

        JwtAuthenticationRequest authenticationRequest = JwtAuthenticationRequest.builder()
                .username(superAdminLoginRequest.getUsername())
                .password(superAdminLoginRequest.getNewPassword())
                .build();

        User user = this.userService.findByUsername(authenticationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
        userService.save(user);


        UserTokenState token = this.login(authenticationRequest);
        return ResponseEntity.ok(token);
    }


    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }


    @PostMapping("/register")
    public ResponseEntity<UserTokenState> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {
        if(userRequest.getFirstname() == null || userRequest.getLastname() == null || userRequest.getUsername() == null
                || userRequest.getPassword() == null || userRequest.getEmail() == null || userRequest.getRoleEnum() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        User existUser = this.userService.findByUsername(userRequest.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(existUser.getId(), "Username already exists");
        }

        existUser = this.userService.findByEmail(userRequest.getEmail());

        if (existUser != null) {
            throw new ResourceConflictException(existUser.getId(), "Email already exists");
        }

        List<Role> roles;
        if(userRequest.getRoleEnum() == RoleEnum.SUPER_ADMIN){
            roles = roleService.findByName("ROLE_SUPER_ADMIN");
        }else if(userRequest.getRoleEnum() == RoleEnum.ADMIN){
            roles = roleService.findByName("ROLE_ADMIN");
        }else{
            roles = roleService.findByName("ROLE_USER");
        }

        User user = User.builder()
                .firstName(userRequest.getFirstname())
                .lastName(userRequest.getLastname())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .joinedDate(new Date())
                .roleEnum(userRequest.getRoleEnum())
                .roles(roles)
                .enabled(true)
                .passwordChanged(false)
                .userBadge(UserBadge.NONE)
                .lastPasswordResetDate(new Timestamp(System.currentTimeMillis()))
                .build();

        user = this.userService.save(user);

        //kada se registrovao korisnik neka se odmah i loguje (tj. dobije svoj token i postavi u kontekst)
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest(userRequest.getUsername(), userRequest.getPassword());
        UserTokenState token = this.login(jwtAuthenticationRequest);

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> addAdmin(@RequestBody UserRequest userRequest) {
        if(userRequest.getFirstname() == null || userRequest.getLastname() == null || userRequest.getUsername() == null
            || userRequest.getPassword() == null || userRequest.getEmail() == null || userRequest.getRoleEnum() == null){
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        try {
            User existUser = this.userService.findByUsername(userRequest.getUsername());

            if (existUser != null) {
                return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
            }

            existUser = this.userService.findByEmail(userRequest.getEmail());

            if (existUser != null) {
                return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
            }

            // Force ADMIN role regardless of what's sent in the request
            List<Role> roles = roleService.findByName("ROLE_ADMIN");

            User user = User.builder()
                .firstName(userRequest.getFirstname())
                .lastName(userRequest.getLastname())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .joinedDate(new Date())
                .roleEnum(RoleEnum.ADMIN)  // Force ADMIN role
                .roles(roles)
                .enabled(true)
                .passwordChanged(false)
                .userBadge(UserBadge.NONE)
                .lastPasswordResetDate(new Timestamp(System.currentTimeMillis()))
                .build();

            user = this.userService.save(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating admin user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String authToken = tokenUtils.getToken(request);
        if (authToken != null) {
            tokenUtils.blacklistToken(authToken);
        }
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}