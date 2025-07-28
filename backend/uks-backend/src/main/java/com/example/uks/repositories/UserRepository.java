package com.example.uks.repositories;

import com.example.uks.enumeration.Role;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);

    User findByUsername(String email);

    List<User> findByRole(Role role);

    List<User> findByUserBadge(UserBadge userBadge);

}
