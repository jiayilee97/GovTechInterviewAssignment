package com.govtech.assignment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UsersRequestDto {
    private BigDecimal min = BigDecimal.valueOf(0.0);
    private BigDecimal max = BigDecimal.valueOf(4000.0);
    private int offset = 0;
    private Integer limit;
    private String sort;
}
