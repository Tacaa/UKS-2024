package com.example.uks.controllers;

import com.example.uks.dto.tag.CreateTagDTO;
import com.example.uks.dto.tag.TagDTO;
import com.example.uks.dto.team.TeamDTO;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.Tag;
import com.example.uks.model.Team;
import com.example.uks.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}