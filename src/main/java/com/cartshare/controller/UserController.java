package com.cartshare.controller;

import com.cartshare.model.Household;
import com.cartshare.model.User;
import com.cartshare.repository.HouseholdRepository;
import com.cartshare.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    private final HouseholdRepository householdRepository;

    public UserController(UserRepository userRepository, HouseholdRepository householdRepository) {
        this.userRepository = userRepository;
        this.householdRepository = householdRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // In a real app we would hash the password here!
        return userRepository.save(user);
    }

    @PutMapping("/{userId}/join/{inviteCode}")
    public User joinHousehold(@PathVariable Long userId, @PathVariable String inviteCode) {
        User user = userRepository.findById(userId).orElseThrow();
        Household household = householdRepository.findByInviteCode(inviteCode).orElseThrow();

        user.setHousehold(household);
        return userRepository.save(user);
    }
}