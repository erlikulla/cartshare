package com.cartshare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity = 1;

    private BigDecimal price; // Set dynamically when marked as 'PURCHASED'

    @Column(nullable = false)
    private boolean isBill; // true = Bills view, false = Cart view

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemStatus status = ItemStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "claimed_by")
    private User claimedBy;

    private LocalDateTime createdAt = LocalDateTime.now();
}