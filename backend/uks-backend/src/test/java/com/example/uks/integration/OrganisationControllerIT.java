package com.example.uks.integration;

import com.example.uks.dto.organisation.OrganisationCreateDTO;
import com.example.uks.dto.organisation.OrganisationDTO;
import com.example.uks.dto.organisation.OrganisationUpdateDTO;
import com.example.uks.model.Organisation;
import com.example.uks.repositories.OrganisationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrganisationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganisationRepository organisationRepository;

    private OrganisationUpdateDTO updateDTO;

    private OrganisationCreateDTO createDTO;

    private int testOrgId=1;

    @Test
    public void updateOrganisation() throws Exception {
        updateDTO = new OrganisationUpdateDTO();
        updateDTO.setDescription("Novi opis");
        updateDTO.setOwnerId(1);

        mockMvc.perform(put("/api/organisation/"+testOrgId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.description").value("Novi opis"));
    }

    @Test
    public void createOrganisation() throws Exception {
        createDTO = new OrganisationCreateDTO();
        createDTO.setOwnerId(1);
        createDTO.setName("Nova org");
        createDTO.setDescription("Novi opis");

        mockMvc.perform(post("/api/organisation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("Nova org"))
                .andExpect(jsonPath("$.data.description").value("Novi opis"));

    }

    @Test
    public void getOrganisationById() throws Exception {
        Optional<Organisation> dto = organisationRepository.findById(testOrgId);
        mockMvc.perform(
                        get("/api/organisation/"+testOrgId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(dto.get().getName()));
    }
}