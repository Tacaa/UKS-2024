package com.example.uks.controllers;

import com.example.uks.dto.tag.CreateTagDTO;
import com.example.uks.dto.tag.TagDTO;
import com.example.uks.dto.team.TeamDTO;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.exceptions.TagNotFoundException;
import com.example.uks.model.Tag;
import com.example.uks.model.Team;
import com.example.uks.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTag(@RequestBody CreateTagDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Tag tag = tagService.createTag(dto);
            response.put("message", "Tag successfully created");
            response.put("data", TagDTO.from(tag));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RepositoryNotFoundException e) {
            response.put("message", "Repository not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{repositoryId}")
    public ResponseEntity<List<TagDTO>> getTagsByRepository(@PathVariable Long repositoryId) {
        List<Tag> tags = tagService.getAllTagsByRepository(repositoryId);
        List<TagDTO> tagDTOs = tags.stream()
                .map(TagDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tagDTOs);
    }

    //poziv --> /api/tags/search?query=cms  (query je rijec koju prosljedjujete za pretragu)
    //sortira se po vremenu pullovanja
    @GetMapping("/search")
    public ResponseEntity<List<TagDTO>> searchTags(@RequestParam String query) {
        List<Tag> tags = tagService.searchAndSortTags(query);

        return ResponseEntity.ok(tags.stream()
                .map(TagDTO::from)
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Integer id) {
        try {
            tagService.delete(id);
            return ResponseEntity.ok("Tag successfully deleted!");
        } catch (TagNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}