package com.example.uks.util.specification;

import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Repository;
import org.springframework.data.jpa.domain.Specification;

public class OfficialRepositorySpecifications {
    public static Specification<Repository> ofTypeOfficial() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.literal("Official"), root.type());
    }
}