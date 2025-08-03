package com.example.uks.analytics;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogSearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final LogQueryParser logQueryParser;

    public List<LogDocument> searchLogs(String queryString) {
        CriteriaQuery query = logQueryParser.parseToCriteriaQuery(queryString);

        SearchHits<LogDocument> searchHits = elasticsearchOperations.search(query, LogDocument.class);

        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
