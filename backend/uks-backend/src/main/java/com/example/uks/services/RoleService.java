package com.example.uks.services;

import com.example.uks.model.Role;
import com.example.uks.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Integer id) {
        Role auth = this.roleRepository.getReferenceById(id);
        return auth;
    }
    
    public List<Role> findByName(String name) {
        List<Role> roles = this.roleRepository.findByName(name);
        return roles;
    }
}
