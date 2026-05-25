package com.cartshare.service;

import com.cartshare.model.Item;
import com.cartshare.model.ItemStatus;
import com.cartshare.model.User;
import com.cartshare.repository.ItemRepository;
import com.cartshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    // 1. Claim an item ("I've got it!")
    public Item claimItem(Long itemId, Long userId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        item.setClaimedBy(user);
        item.setStatus(ItemStatus.CLAIMED);
        return itemRepository.save(item);
    }

    // 2. Unclaim an item (If a roommate changes their mind)
    public Item unclaimItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setClaimedBy(null);
        item.setStatus(ItemStatus.PENDING);
        return itemRepository.save(item);
    }

    // 3. Mark as Purchased (Checkout / Clear from active list)
    public Item markAsPurchased(Long itemId, BigDecimal actualPrice) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setPrice(actualPrice); // Records how much was actually spent for the balance calculator
        item.setStatus(ItemStatus.PURCHASED);
        return itemRepository.save(item);
    }
}