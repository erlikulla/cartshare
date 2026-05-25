package com.cartshare.service;

import com.cartshare.dto.BalanceDTO;
import com.cartshare.model.Household;
import com.cartshare.model.Item;
import com.cartshare.model.ItemStatus;
import com.cartshare.model.User;
import com.cartshare.repository.ItemRepository;
import com.cartshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseholdService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public HouseholdService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public List<BalanceDTO> calculateHouseholdBalances(Long householdId, List<User> members) {
        List<Item> purchasedItems = itemRepository.findByHouseholdIdAndStatus(householdId, ItemStatus.PURCHASED);
        Map<Long, BigDecimal> individualSpending = new HashMap<>();

        for (User member : members) {
            individualSpending.put(member.getId(), BigDecimal.ZERO);
        }

        BigDecimal totalHouseholdExpenses = BigDecimal.ZERO;

        for (Item item : purchasedItems) {
            if (item.getClaimedBy() != null && item.getPrice() != null) {
                Long buyerId = item.getClaimedBy().getId();
                BigDecimal cost = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

                individualSpending.put(buyerId, individualSpending.get(buyerId).add(cost));
                totalHouseholdExpenses = totalHouseholdExpenses.add(cost);
            }
        }

        List<BalanceDTO> balances = new ArrayList<>();
        if (members.isEmpty()) return balances;

        BigDecimal memberCount = BigDecimal.valueOf(members.size());
        BigDecimal splitShare = totalHouseholdExpenses.divide(memberCount, 2, RoundingMode.HALF_UP);

        for (User member : members) {
            BigDecimal actualPaid = individualSpending.get(member.getId());
            BigDecimal netBalance = actualPaid.subtract(splitShare);

            balances.add(new BalanceDTO(member.getUsername(), netBalance));
        }

        return balances;
    }
}