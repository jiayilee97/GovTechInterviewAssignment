package com.govtech.assignment.entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CsvToUser  {
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private BigDecimal salary;

}
