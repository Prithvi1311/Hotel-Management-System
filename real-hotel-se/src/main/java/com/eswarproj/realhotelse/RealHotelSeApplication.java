package com.eswarproj.realhotelse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealHotelSeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealHotelSeApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.boot.CommandLineRunner demo(
			com.eswarproj.realhotelse.repository.RoleRepository roleRepository,
			com.eswarproj.realhotelse.repository.UserRepository userRepository,
			com.eswarproj.realhotelse.repository.RoomRepository roomRepository,
			org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
		return (args) -> {
			// 1. Create Roles
			if (!roleRepository.existsByName("ROLE_ADMIN"))
				roleRepository.save(new com.eswarproj.realhotelse.model.Role("ROLE_ADMIN"));
			if (!roleRepository.existsByName("ROLE_USER"))
				roleRepository.save(new com.eswarproj.realhotelse.model.Role("ROLE_USER"));

			// 2. Create Admin
			if (!userRepository.existsByEmail("admin@hotel.com")) {
				com.eswarproj.realhotelse.model.User admin = new com.eswarproj.realhotelse.model.User();
				admin.setEmail("admin@hotel.com");
				admin.setPassword(passwordEncoder.encode("password"));
				admin.setFirstName("Admin");
				admin.setLastName("User");
				admin.setRoles(java.util.Collections.singletonList(roleRepository.findByName("ROLE_ADMIN").get()));
				userRepository.save(admin);
				System.out.println("Admin Account Created");
			}

			// 3. Create Rooms
			if (roomRepository.count() == 0) {
				roomRepository.save(new com.eswarproj.realhotelse.model.Room(null, "Single Standard Room",
						new java.math.BigDecimal("100.00"), false, null, null));
				roomRepository.save(new com.eswarproj.realhotelse.model.Room(null, "Double Sea View Room",
						new java.math.BigDecimal("250.00"), false, null, null));
				roomRepository.save(new com.eswarproj.realhotelse.model.Room(null, "Family Suite",
						new java.math.BigDecimal("400.00"), false, null, null));
				System.out.println("Sample Rooms Created");
			}
		};
	}
}
