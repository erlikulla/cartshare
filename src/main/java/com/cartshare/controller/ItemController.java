package com.cartshare.controller;

import com.cartshare.model.Item;
import com.cartshare.model.ItemStatus;
import com.cartshare.repository.ItemRepository;
import com.cartshare.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    public ItemController(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    // Fetches either active Groceries OR active Bills based on the boolean flag
    @GetMapping("/household/{householdId}/active")
    public List<Item> getActiveItems(@PathVariable Long householdId, @RequestParam boolean isBill) {
        return itemRepository.findByHouseholdIdAndIsBillAndStatusNot(householdId, isBill, ItemStatus.PURCHASED);
    }

    // Fetches the History log
    @GetMapping("/household/{householdId}/history")
    public List<Item> getHistory(@PathVariable Long householdId) {
        return itemRepository.findByHouseholdIdAndStatus(householdId, ItemStatus.PURCHASED);
    }

    @PutMapping("/{itemId}/claim/{userId}")
    public Item claimItem(@PathVariable Long itemId, @PathVariable Long userId) {
        return itemService.claimItem(itemId, userId);
    }

    @PutMapping("/{itemId}/unclaim")
    public Item unclaimItem(@PathVariable Long itemId) {
        return itemService.unclaimItem(itemId);
    }

    @PutMapping("/{itemId}/purchase")
    public Item purchaseItem(@PathVariable Long itemId, @RequestParam BigDecimal price) {
        return itemService.markAsPurchased(itemId, price);
    }
}