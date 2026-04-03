package com.zorvyn.project.Model;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class DashboardResponse {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal netBalance;

    private Map<String, BigDecimal> categoryWiseTotals;

    private List<?> recentTransactions;

    private List<MonthlyTrend> monthlyTrends;
}