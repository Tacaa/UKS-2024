package com.example.uks.integration;

import com.example.uks.dto.user.BadgeDTO;
import com.example.uks.dto.user.UpdateUserDTO;
import com.example.uks.enumeration.UserBadge;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private int userId = 1;

    @Autowired
    private ObjectMapper objectMapper;

    private UpdateUserDTO updateUserDTO;

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/api/users/"+userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform((get("/api/users/all")
                .contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUserBadge() throws Exception {
        BadgeDTO badgeDTO = new BadgeDTO(UserBadge.VERIFIED_PUBLISHER);

        mockMvc.perform((put("/api/users/badge/"+userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(badgeDTO))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userBadge").value("VERIFIED_PUBLISHER"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setFirstName("Marko");
        updateUserDTO.setLastName("Tomic");
        updateUserDTO.setUsername("markotomic");
        updateUserDTO.setEmail("marko@tomic.com");

        mockMvc.perform(put("/api/users/"+userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.firstName").value("Marko"))
                .andExpect(jsonPath("$.data.lastName").value("Tomic"))
                .andExpect(jsonPath("$.data.username").value("markotomic"))
                .andExpect(jsonPath("$.data.email").value("marko@tomic.com"));
    }

    @Test
    public void testGetUsersByBadge() throws Exception {

        mockMvc.perform(get("/api/users/badge")
                        .param("badge","VERIFIED_PUBLISHER")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
