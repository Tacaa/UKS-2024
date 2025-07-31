package com.example.uks.appstartup;

import com.example.uks.enumeration.Role;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.model.User;
import com.example.uks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    public void superAdminCreation(String password) throws ParseException {
        User superAdmin = new User();
        superAdmin.setFirstName("Super");
        superAdmin.setLastName("Admin");
        superAdmin.setUsername("superadmin");
        superAdmin.setPassword(password);
        superAdmin.setEmail("super@admin.com");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formattedDate = formatter.format(new Date());
        Date date = formatter.parse(formattedDate);
        superAdmin.setJoinedDate(date);
        superAdmin.setRole(Role.SUPER_ADMIN);
        superAdmin.setPasswordChanged(false);
        superAdmin.setUserBadge(UserBadge.NONE);

        userRepository.save(superAdmin);
        System.out.println("Super-Admin created.");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<User> superAdmins = userRepository.findByRole(Role.SUPER_ADMIN);

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
