package com.example.uks.appstartup;

import com.example.uks.enumeration.RoleEnum;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.model.Role;
import com.example.uks.model.User;
import com.example.uks.repositories.UserRepository;
import com.example.uks.services.RoleService;
import com.example.uks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void superAdminCreation(String password) throws ParseException {

        List<Role> roles = roleService.findByName("ROLE_SUPER_ADMIN");

        User superAdmin  = User.builder()
                .firstName("Super")
                        .lastName("Admin")
                                .email("super@admin.com")
                                        .username("superadmin")
                                                .password(password)
                                                        .joinedDate(new Date())
                                                                .roleEnum(RoleEnum.SUPER_ADMIN)
                                                                        .roles(roles)
                                                                                .enabled(true)
                                                                                        .passwordChanged(false)
                                                                                                .userBadge(UserBadge.NONE)
                                                                                                        .lastPasswordResetDate( new Timestamp(System.currentTimeMillis()))
                                                                                                                .build();

        userService.save(superAdmin);

        System.out.println("Super-Admin created.");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<User> superAdmins = userRepository.findByRoleEnum(RoleEnum.SUPER_ADMIN);

        for (User superAdminUser : superAdmins){
            System.out.println("Super-admin is: "+superAdminUser.getUsername());
        }

        if(superAdmins.size() == 0){
            System.out.println("No assigned Super-Admin...");
            String password = CreateFileOnDesktop.createPassowordFile();
            try {
                superAdminCreation(password);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Super-Admin already exists!");
        }
    }
}
