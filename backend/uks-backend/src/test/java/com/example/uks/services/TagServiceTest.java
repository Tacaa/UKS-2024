package com.example.uks.services;

import com.example.uks.dto.tag.CreateTagDTO;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.exceptions.TagNotFoundException;
import com.example.uks.model.Repository;
import com.example.uks.model.Tag;
import com.example.uks.repositories.RepositoryRepository;
import com.example.uks.repositories.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private RepositoryRepository repositoryRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTag_success() {
        CreateTagDTO dto = new CreateTagDTO("v1.0", "docker pull repo:v1.0", "john", 1);

        Repository repository = new Repository();
        repository.setId(1);

        when(repositoryRepository.findById(1)).thenReturn(Optional.of(repository));

        Tag savedTag = new Tag();
        savedTag.setName("v1.0");

        when(tagRepository.save(any(Tag.class))).thenReturn(savedTag);

        Tag returned = tagService.createTag(dto);

        assertEquals("v1.0",returned.getName());
        verify(tagRepository).save(any(Tag.class));
    }

    @Test
    void createTag_RepoNotFound(){
        CreateTagDTO dto = new CreateTagDTO("v1.0", "docker pull repo:v1.0", "john", 1);

        when(repositoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RepositoryNotFoundException.class, ()-> tagService.createTag(dto));
    }

    @Test
    void getAllTagsByRepository() {
        List<Tag> tags = List.of(new Tag(), new Tag());

        when(tagRepository.findAllByRepositoryIdAndDeletedFalse(1L)).thenReturn(tags);

        List<Tag> returned = tagService.getAllTagsByRepository(1L);

        assertEquals(2, returned.size());
    }

    @Test
    void delete_succes() {
        Tag tag = new Tag();
        tag.setDeleted(false);

        when(tagRepository.findByIdAndDeletedFalse(1)).thenReturn(Optional.of(tag));

        tagService.delete(1);

        assertTrue(tag.getDeleted());
        verify(tagRepository).save(tag);
    }

    @Test
    void delete_tagNotFound(){
        when(tagRepository.findByIdAndDeletedFalse(1)).thenReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> tagService.delete(1));
    }
}