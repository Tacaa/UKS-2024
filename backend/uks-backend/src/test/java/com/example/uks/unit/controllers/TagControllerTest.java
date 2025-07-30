package com.example.uks.unit.controllers;

import com.example.uks.controllers.TagController;
import com.example.uks.dto.tag.CreateTagDTO;
import com.example.uks.dto.tag.TagDTO;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.exceptions.TagNotFoundException;
import com.example.uks.model.Repository;
import com.example.uks.model.Tag;
import com.example.uks.services.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TagControllerTest {

    @InjectMocks
    private TagController tagController;

    @Mock
    private TagService tagService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTag_success() {
        CreateTagDTO dto = new CreateTagDTO("v1.0", "docker pull repo:v1.0", "john", 1);

        Repository repository = new Repository();
        repository.setId(1);

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("v1.0");
        tag.setDockerPullCommand("docker pull repo:v1.0");
        tag.setRepository(repository);

        when(tagService.createTag(any())).thenReturn(tag);

        ResponseEntity<Map<String, Object>> response = tagController.createTag(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Tag successfully created", response.getBody().get("message"));
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    void createTag_RepoNotFound(){
        CreateTagDTO dto = new CreateTagDTO("v1.0", "docker pull repo:v1.0", "john", 1);

        when(tagService.createTag(any())).thenThrow(new RepositoryNotFoundException("Repo not found"));

        ResponseEntity<Map<String, Object>> response = tagController.createTag(dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Repository not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void getTagsByRepository() {
        Tag tag1 = new Tag();
        tag1.setId(1);
        tag1.setName("v1");

        Tag tag2 = new Tag();
        tag2.setId(2);
        tag2.setName("v2");

        when(tagService.getAllTagsByRepository(1L)).thenReturn(List.of(tag1, tag2));

        ResponseEntity<List<TagDTO>> response = tagController.getTagsByRepository(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("v1", response.getBody().get(0).getName());
    }

    @Test
    void searchTags() {
        Tag tag = new Tag();
        tag.setName("search-hit");

        when(tagService.searchAndSortTags("search")).thenReturn(List.of(tag));

        ResponseEntity<List<TagDTO>> response = tagController.searchTags("search");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("search-hit", response.getBody().get(0).getName());
    }

    @Test
    void deleteTag_success() {
        doNothing().when(tagService).delete(1);

        ResponseEntity<String> response = tagController.deleteTag(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tag successfully deleted!", response.getBody());
    }

    @Test
    void deleteTag_NotFound(){
        doThrow(new TagNotFoundException("Not found")).when(tagService).delete(1);

        ResponseEntity<String> response = tagController.deleteTag(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found", response.getBody());
    }
}