package com.govtech.assignment.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CsvToUser  {
    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Salary")
    private BigDecimal salary;

}
