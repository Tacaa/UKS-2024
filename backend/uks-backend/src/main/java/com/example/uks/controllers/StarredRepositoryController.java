package com.example.uks.controllers;

import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.dto.star.StarDTO;
import com.example.uks.dto.star.StarredRepositoryDTO;
import com.example.uks.dto.tag.TagDTO;
import com.example.uks.exceptions.CanNotStarRepositoryException;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.StarredRepository;
import com.example.uks.model.Tag;
import com.example.uks.services.StarredRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stars")
public class StarredRepositoryController {

    @Autowired
    private StarredRepositoryService starredRepositoryService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> starRepository(@RequestBody StarDTO starDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            StarredRepository repo = starredRepositoryService.starRepository(starDTO);
            response.put("message", "Successfully starred!");
            response.put("data", StarredRepositoryDTO.from(repo));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (CanNotStarRepositoryException e) {
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (RepositoryNotFoundException | UserNotFound e) {
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @DeleteMapping
    public ResponseEntity<Map<String, Object>> unstarRepository(@RequestBody StarDTO starDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            starredRepositoryService.unstarRepository(starDTO);
            response.put("message", "Successfully unstarred!");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } catch (RepositoryNotFoundException | UserNotFound e) {
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @GetMapping("/user")
    public ResponseEntity<List<RepositoryDTO>> getStarredRepositories(@RequestParam Integer userId) {
        return ResponseEntity.ok(starredRepositoryService.getStarredRepositories(userId));
    }

    @GetMapping("/count/{repositoryId}")
    public ResponseEntity<Integer> countStars(@PathVariable Integer repositoryId) {
        return ResponseEntity.ok(starredRepositoryService.countStars(repositoryId));
    }
}

