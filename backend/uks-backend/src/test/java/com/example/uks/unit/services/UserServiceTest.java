package com.example.uks.unit.services;

import com.example.uks.dto.user.BadgeDTO;
import com.example.uks.dto.user.UpdateUserDTO;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.exceptions.AttributeNullException;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.User;
import com.example.uks.repositories.UserRepository;
import com.example.uks.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setEmail("john@doe.com");
    }

    @Test
    void findUserById_returnUser() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        User returned = userService.findUserById(1);

        assertEquals(user, returned);
    }

    @Test
    void findUserById_returnNull() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        User returned = userService.findUserById(2);

        assertNull(returned);
    }

    @Test
    void findAllUsers() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> returned = userService.findAllUsers();

        assertEquals(users,returned);
    }

    @Test
    void testFindAllUsersWithPagination() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> page = new PageImpl<>(List.of(user));

        when(userRepository.findAll(pageable)).thenReturn(page);

        Page<User> returned = userService.findAllUsers(pageable);

        assertEquals(1, returned.getContent().size());
        assertEquals(user, returned.getContent().get(0));
    }

    @Test
    void addBadge_success() {
        BadgeDTO badgeDTO = new BadgeDTO();
        badgeDTO.setUserBadge(UserBadge.SPONSORED_OSS);

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User returned = userService.addBadge(1, badgeDTO);

        assertEquals(UserBadge.SPONSORED_OSS, returned.getUserBadge());
        verify(userRepository).save(user);
    }

    @Test
    void addBadge_userNotFound() {

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        User returned = userService.addBadge(1, new BadgeDTO());

        assertNull(returned);
    }

    @Test
    void updateUser_success() {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setFirstName("Shemsa");
        dto.setLastName("Jasarovic");
        dto.setUsername("slatkigreh");
        dto.setEmail("lepa@brena.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("slatkigreh")).thenReturn(null);
        when(userRepository.findByEmail("lepa@brena.com")).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User returned = userService.updateUser(1, dto);

        assertEquals("Shemsa", returned.getFirstName());
        assertEquals("Jasarovic", returned.getLastName());
        assertEquals("slatkigreh", returned.getUsername());
        assertEquals("lepa@brena.com", returned.getEmail());
    }

    @Test
    void updateUser_userNotFound() {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setFirstName("Shemsa");
        dto.setLastName("Jasarovic");
        dto.setUsername("slatkigreh");
        dto.setEmail("lepa@brena.com");

        when(userRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class, ()-> userService.updateUser(2, dto));
    }

    @Test
    void updateUser_usernameExists() {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setFirstName("Shemsa");
        dto.setLastName("Jasarovic");
        dto.setUsername("slatkigreh");
        dto.setEmail("lepa@brena.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("slatkigreh")).thenReturn(new User());

        assertThrows(AttributeNullException.class, ()-> userService.updateUser(1, dto));
    }

    @Test
    void updateUser_emailExists() {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setFirstName("Shemsa");
        dto.setLastName("Jasarovic");
        dto.setUsername("slatkigreh");
        dto.setEmail("lepa@brena.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("slatkigreh")).thenReturn(null);
        when(userRepository.findByEmail("lepa@brena.com")).thenReturn(new User());

        assertThrows(AttributeNullException.class, ()-> userService.updateUser(1, dto));
    }

    @Test
    void updateUser_firstNameNull() {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setFirstName(null);
        dto.setLastName("Jasarovic");
        dto.setUsername("slatkigreh");
        dto.setEmail("lepa@brena.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertThrows(AttributeNullException.class, ()-> userService.updateUser(1, dto));
    }
}