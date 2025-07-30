package com.example.uks.integration;

import com.example.uks.dto.tag.CreateTagDTO;
import com.example.uks.model.Repository;
import com.example.uks.model.Tag;
import com.example.uks.repositories.RepositoryRepository;
import com.example.uks.repositories.TagRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TagControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private TagRepository tagRepository;

    private int repositoryId;
    private  int tagId;

    private CreateTagDTO createTagDTO;

    @BeforeEach
    public void setUp() {
        Optional<Repository> repoOpt = repositoryRepository.findAll().stream().findFirst();
        if (repoOpt.isPresent()) {
            repositoryId = repoOpt.get().getId();
        }

        Tag tag = new Tag();
        tag.setName("v1.0");
        tag.setDockerPullCommand("docker pull v1.0");
        tag.setRepository(repoOpt.get());
        tag = tagRepository.save(tag);
        tagId = tag.getId();
    }

    @Test
    public void testCreateTag() throws Exception {
        createTagDTO = new CreateTagDTO();
        createTagDTO.setName("v2.0");
        createTagDTO.setRepositoryId(repositoryId);
        createTagDTO.setDockerPullCommand("docker pull v2.0");

        mockMvc.perform(post("/api/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createTagDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("v2.0"));
    }

    @Test
    public void testGetTagsByRepo() throws Exception {
        mockMvc.perform(get("/api/tags/"+repositoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[5].name").value("v1.0"));
    }

    @Test
    public void testDeleteTag() throws Exception {
        mockMvc.perform(delete("/api/tags/"+tagId))
                .andExpect(status().isOk())
                .andExpect(content().string("Tag successfully deleted!"));
    }
}
