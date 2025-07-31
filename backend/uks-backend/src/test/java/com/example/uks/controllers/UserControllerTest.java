package com.example.uks.controllers;

import com.example.uks.dto.user.BadgeDTO;
import com.example.uks.dto.user.UpdateUserDTO;
import com.example.uks.dto.user.UserDTO;
import com.example.uks.dto.util.PagedResponse;
import com.example.uks.exceptions.AttributeNotUniqueException;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.User;
import com.example.uks.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private  UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser_whenUserExists_shouldReturnUser() {
        User user = new User();
        user.setId(1);
        when(userService.findUserById(1)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.getUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void getUser_whenUserDoesNotExist_shouldReturnNotFound() {
        when(userService.findUserById(1)).thenReturn(null);

        ResponseEntity<UserDTO> response = userController.getUser(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userService.findAllUsers()).thenReturn(users);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getUserPage() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        Page<User> userPage = new PageImpl<>(users);
        when(userService.findAllUsers(any(Pageable.class))).thenReturn(userPage);

        ResponseEntity<PagedResponse<UserDTO>> response = userController.getUserPage(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    void filterUsers_shouldReturnFilteredResponse() {
        User user = new User();
        user.setId(1);
        user.setFirstName("John");

        List<User> users = new ArrayList<>();
        users.add(user);

        Page<User> userPage = new PageImpl<>(users, Pageable.unpaged(), users.size());
        when(userService.findUsersByField(eq("John"), eq(null), eq(null), eq(null), eq(null), any(Pageable.class)))
                .thenReturn(userPage);

        ResponseEntity<PagedResponse<UserDTO>> response = userController.filterUsers("John", null, null, null, null, Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());

        UserDTO returnedUser = response.getBody().getContent().get(0);
        assertEquals(1, returnedUser.getId());
        assertEquals("John", returnedUser.getFirstName());
    }

    @Test
    void addUserBadge_whenUserExists() {
        User user = new User();
        BadgeDTO badgeDTO = new BadgeDTO();
        when(userService.addBadge(1, badgeDTO)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.addUserBadge(1, badgeDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void addUserBadge_whenUserDoesNotExists() {
        when(userService.addBadge(1, new BadgeDTO())).thenReturn(null);

        ResponseEntity<UserDTO> response = userController.addUserBadge(1, new BadgeDTO());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateUser_whenSuccess_shouldReturnUpdatedUser() {
        User user = new User();
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        when(userService.updateUser(1, updateUserDTO)).thenReturn(user);

        ResponseEntity<Map<String, Object>> response = userController.updateUser(1, updateUserDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().get("message"));
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    void updateUser_whenNotFound_shouldReturnNotFound() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        when(userService.updateUser(1, updateUserDTO)).thenThrow(new RepositoryNotFoundException("User not found"));

        ResponseEntity<Map<String, Object>> response = userController.updateUser(1, updateUserDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("User not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void updateUser_whenValidationFails_shouldReturnBadRequest() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        when(userService.updateUser(1, updateUserDTO)).thenThrow(new AttributeNotUniqueException("Email must be unique"));

        ResponseEntity<Map<String, Object>> response = userController.updateUser(1, updateUserDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Email must be unique", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }
}