package com.example.uks.integration;

import com.example.uks.dto.team.AddTeamMemberDTO;
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
public class TeamControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private int orgId = 1;

    private int userId = 1;


    @Autowired
    private ObjectMapper objectMapper;

    private AddTeamMemberDTO dto;

    @Test
    public void testGetTeamsByOrg() throws Exception {
        mockMvc.perform(get("/api/team/" + orgId + "?userId="+userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddMemberToTeam() throws Exception {

        dto= new AddTeamMemberDTO();
        dto.setMemberId(1);
        dto.setTeamId(1);

        mockMvc.perform(post("/api/team/add_member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
