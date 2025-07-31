package com.example.uks.services;

import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.dto.star.StarDTO;
import com.example.uks.exceptions.CanNotStarRepositoryException;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.exceptions.StarNotFound;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.*;
import com.example.uks.repositories.RepositoryRepository;
import com.example.uks.repositories.StarredRepositoryRepository;
import com.example.uks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarredRepositoryService {

    @Autowired
    private StarredRepositoryRepository starredRepoRepo;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private UserRepository userRepository;

    private boolean isUserMemberOfOrganisation(User user, Organisation organisation) {
        if (organisation == null) {
            return false;
        }

        if (organisation.getMembers().contains(user)) {
            return true;
        }

        for (Team team : organisation.getTeams()) {
            if (team.getMembers().contains(user)) {
                return true;
            }
        }

        return false;
    }

    public StarredRepository starRepository(StarDTO starDTO) {
        Repository repo = repositoryRepository.findById(starDTO.getRepositoryId())
                .orElseThrow(() -> new RepositoryNotFoundException("Repository not found"));

        User user = userRepository.findById(starDTO.getUserId())
                .orElseThrow(() -> new UserNotFound("User not found"));

        if (starredRepoRepo.existsByUserIdAndRepositoryId(starDTO.getUserId(), starDTO.getRepositoryId())) {
            throw new CanNotStarRepositoryException("Existed star for this user");
        }

        Organisation org = repo.getOrganisation();
        if (this.isUserMemberOfOrganisation(user, org)) {
            throw new CanNotStarRepositoryException("Cannot star your own or organisation’s repository.");
        }

        StarredRepository starred = new StarredRepository();
        starred.setUser(user);
        starred.setRepository(repo);
        return starredRepoRepo.save(starred);
    }

    public List<RepositoryDTO> getStarredRepositories(Integer userId) {
        List<StarredRepository> starred = starredRepoRepo.findByUserId(userId);

        List<Integer> repositoryIds = starred.stream()
                .map(sr -> sr.getRepository().getId())
                .toList();

        List<Repository> repositories = repositoryRepository.findAllByIdIn(repositoryIds);

        return repositories.stream()
                .map(RepositoryDTO::new)
                .collect(Collectors.toList());
    }

    public Integer countStars(Integer repositoryId) {
        return starredRepoRepo.countByRepositoryId(repositoryId);
    }

    public void unstarRepository(Integer id) {
        StarredRepository star = starredRepoRepo.findById(id)
                .orElseThrow(() -> new StarNotFound("Star not found"));
        starredRepoRepo.delete(star);
    }
}

