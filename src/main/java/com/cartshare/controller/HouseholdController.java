package com.cartshare.controller;

import com.cartshare.dto.BalanceDTO;
import com.cartshare.model.Household;
import com.cartshare.repository.HouseholdRepository;
import com.cartshare.service.HouseholdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
@CrossOrigin(origins = "*")
public class HouseholdController {

    private final HouseholdRepository householdRepository;
    private final HouseholdService householdService;

    public HouseholdController(HouseholdRepository householdRepository, HouseholdService householdService) {
        this.householdRepository = householdRepository;
        this.householdService = householdService;
    }

    @PostMapping("/create")
    public Household createHousehold(@RequestBody Household household) {
        // Generates a simple random 6-character invite code
        household.setInviteCode(Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 6).toUpperCase());
        return householdRepository.save(household);
    }

    @GetMapping("/{id}/balances")
    public List<BalanceDTO> getBalances(@PathVariable Long id) {
        Household household = householdRepository.findById(id).orElseThrow();
        return householdService.calculateHouseholdBalances(id, household.getMembers());
    }
}