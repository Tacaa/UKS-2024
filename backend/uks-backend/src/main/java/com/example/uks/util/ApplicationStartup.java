package com.example.uks.util;

import com.example.uks.enumeration.Role;
import com.example.uks.model.User;
import com.example.uks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    public void superAdminCreation(String password){
        User superAdmin = new User();
        superAdmin.setId(26);
        superAdmin.setFirstName("Super");
        superAdmin.setLastName("Admin");
        superAdmin.setUsername("superadmin");
        superAdmin.setPassword(password);
        superAdmin.setEmail("super@admin.com");
        superAdmin.setJoinedDate(new Date());
        superAdmin.setRole(Role.SUPER_ADMIN);
        superAdmin.setPasswordChanged(false);
        System.out.println("Uspesno kreiran super admin!");

        userRepository.save(superAdmin);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<User> superAdminUsers = userRepository.findByRole(Role.SUPER_ADMIN);

        for (User superAdminUser : superAdminUsers) {
            System.out.println("Super admin je: " + superAdminUser.getUsername());
        }
        if(superAdminUsers.size() == 0){
            System.out.println("Nema superadmina");
            String password = CreateFileOnDesktop.createPasswordFile();
            superAdminCreation(password);
        } else {
            System.out.println("Vec postoji superadmin!");
        }
    }
}