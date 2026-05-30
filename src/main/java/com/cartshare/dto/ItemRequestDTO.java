package com.cartshare.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDTO {
    private String name;
    private Long householdId;
    private Long addedByUserId;
    private boolean isBill;
    private Integer quantity = 1;
}