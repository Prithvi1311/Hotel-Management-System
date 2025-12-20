package com.eswarproj.realhotelse;

import com.eswarproj.realhotelse.model.Role;
import com.eswarproj.realhotelse.model.Room;
import com.eswarproj.realhotelse.model.User;
import com.eswarproj.realhotelse.repository.RoomRepository;
import com.eswarproj.realhotelse.service.IRoleService;
import com.eswarproj.realhotelse.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final IRoleService roleService;
    private final IUserService userService;
    private final RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            initializeRoles();
            initializeAdmin();
            initializeRooms();
        } catch (Exception e) {
            System.err.println("Database Initializer Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeRoles() {
        if (roleService.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
            roleService.createRole(new Role("ROLE_ADMIN"));
        }
        if (roleService.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_USER"))) {
            roleService.createRole(new Role("ROLE_USER"));
        }
    }

    private void initializeAdmin() {
        // Warning: This implementation of getUser might throw Exception if user not
        // found, depending on implementation
        // Better to try/catch or use repository directly if service behavior is
        // unknown.
        // Assuming getUser returns null or throws specific exception.
        try {
            User existing = userService.getUser("admin@hotel.com");
            if (existing != null)
                return;
        } catch (Exception e) {
            // Likely "User not found" exception, proceed to create
        }

        User admin = new User();
        admin.setEmail("admin@hotel.com");
        admin.setPassword("password");
        admin.setFirstName("Admin");
        admin.setLastName("User");

        Role adminRole = roleService.findByName("ROLE_ADMIN");
        admin.setRoles(Collections.singletonList(adminRole));

        userService.registerUser(admin);
        System.out.println("Admin user created: admin@hotel.com / password");
    }

    private void initializeRooms() {
        if (roomRepository.count() == 0) {
            roomRepository.save(new Room(null, "Single Standard Room", new BigDecimal("100.00"), false, null, null));
            roomRepository.save(new Room(null, "Double Sea View Room", new BigDecimal("250.00"), false, null, null));
            roomRepository.save(new Room(null, "Family Suite", new BigDecimal("400.00"), false, null, null));
            roomRepository.save(new Room(null, "Presidential Suite", new BigDecimal("1000.00"), false, null, null));
            System.out.println("Sample rooms created.");
        }
    }
}
