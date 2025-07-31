package com.example.uks.analytics;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogSearchService logSearchService;

    // Primer: GET /api/logs/search?query=(level:ERROR OR level:WARNING) AND message:"failure"
    @GetMapping("/search")
    public ResponseEntity<List<LogDocument>> searchLogs(@RequestParam String query) {
        List<LogDocument> results = logSearchService.searchLogs(query);
        return ResponseEntity.ok(results);
    }
}
