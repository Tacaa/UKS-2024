package com.example.uks.services;

import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.dto.star.StarDTO;
import com.example.uks.exceptions.CanNotStarRepositoryException;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.exceptions.StarNotFound;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.Repository;
import com.example.uks.model.StarredRepository;
import com.example.uks.model.User;
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

    public StarredRepository starRepository(StarDTO starDTO) {
        Repository repo = repositoryRepository.findById(starDTO.getRepositoryId())
                .orElseThrow(() -> new RepositoryNotFoundException("Repository not found"));

        if (repo.getPersonal() || repo.getOwner().getId().equals(starDTO.getUserId()) || repo.getOrganisation() != null) {
            throw new CanNotStarRepositoryException("Cannot star your own or organisation’s repository.");
        }

        if (starredRepoRepo.existsByUserIdAndRepositoryId(starDTO.getUserId(), starDTO.getRepositoryId())) {
            throw new CanNotStarRepositoryException("Existed star fr this user");
        }

        User user = userRepository.findById(starDTO.getUserId())
                .orElseThrow(() -> new UserNotFound("User not found"));

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

