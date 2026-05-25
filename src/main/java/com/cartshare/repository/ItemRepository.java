package com.cartshare.repository;

import com.cartshare.model.Item;
import com.cartshare.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByHouseholdIdAndIsBillAndStatusNot(Long householdId, boolean isBill, ItemStatus status);
    List<Item> findByHouseholdIdAndStatus(Long householdId, ItemStatus status);
}