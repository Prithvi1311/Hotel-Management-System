package com.eswarproj.realhotelse.controller;

import com.eswarproj.realhotelse.model.Role;
import com.eswarproj.realhotelse.model.Room;
import com.eswarproj.realhotelse.model.User;
import com.eswarproj.realhotelse.repository.RoleRepository;
import com.eswarproj.realhotelse.repository.RoomRepository;
import com.eswarproj.realhotelse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;

@RestController
@RequestMapping("/debug")
@RequiredArgsConstructor
public class DebugController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/reset")
    public ResponseEntity<String> resetSystem() {
        StringBuilder status = new StringBuilder();

        try {
            // 1. Reset Admin
            userRepository.findByEmail("admin@hotel.com").ifPresent(user -> {
                userRepository.delete(user);
                status.append("Deleted existing admin. ");
            });

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

            User admin = new User();
            admin.setEmail("admin@hotel.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRoles(Collections.singletonList(adminRole));
            userRepository.save(admin);
            status.append("Created new Admin (admin@hotel.com / password). ");

            // 2. Reset Rooms
            if (roomRepository.count() == 0) {
                roomRepository
                        .save(new Room(null, "Single Standard Room", new BigDecimal("100.00"), false, null, null));
                roomRepository
                        .save(new Room(null, "Double Sea View Room", new BigDecimal("250.00"), false, null, null));
                roomRepository.save(new Room(null, "Family Suite", new BigDecimal("400.00"), false, null, null));
                roomRepository.save(new Room(null, "Presidential Suite", new BigDecimal("1000.00"), false, null, null));
                status.append("Created 4 sample rooms. ");
            } else {
                status.append("Rooms already exist (skipped). ");
            }

            return ResponseEntity.ok("RESET SUCCESS: " + status.toString());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("RESET FAILED: " + e.getMessage());
        }
    }
}
