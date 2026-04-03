package com.zorvyn.project.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MonthlyTrend {

    private Integer month;
    private BigDecimal income;
    private BigDecimal expense;
}