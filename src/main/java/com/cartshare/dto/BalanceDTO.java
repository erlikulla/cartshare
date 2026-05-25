package com.cartshare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BalanceDTO {
    private String username;
    private BigDecimal balance;
}