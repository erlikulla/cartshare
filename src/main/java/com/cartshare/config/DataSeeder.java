package com.cartshare.config;

import com.cartshare.model.Household;
import com.cartshare.model.User;
import com.cartshare.repository.HouseholdRepository;
import com.cartshare.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, HouseholdRepository householdRepository) {
        return args -> {
            if (householdRepository.count() == 0) {

                // 1. Create a dummy Household with required invite code
                Household household = new Household();
                household.setName("The Roommates");
                household.setInviteCode("ROOM123"); // Filled missing required field
                Household savedHousehold = householdRepository.save(household);

                // 2. Create your test User with required email and password
                User testUser = new User();
                testUser.setUsername("erli.kulla");
                testUser.setEmail("erli@example.com");   // Filled missing required field
                testUser.setPassword("password123");     // Filled missing required field
                testUser.setHousehold(savedHousehold);
                userRepository.save(testUser);

                System.out.println("Database seeded successfully with Household (ID: " + savedHousehold.getId() + ") and User!");
            }
        };
    }
}