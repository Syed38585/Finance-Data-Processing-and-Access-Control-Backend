package com.zorvyn.project.Service;

import com.zorvyn.project.Model.DashboardResponse;
import com.zorvyn.project.Model.FinancialRecord;
import com.zorvyn.project.Model.MonthlyTrend;
import com.zorvyn.project.Repository.FinanceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FinanceRepo financeRepo;

    /**
     * Builds and returns dashboard summary data.
     *
     * This includes total income, total expenses, net balance,
     * category-wise totals, recent transactions, and monthly trends.
     */
    public DashboardResponse getDashboardSummary() {

        // Fetch total income and expense, default to zero if null
        BigDecimal totalIncome = Optional.ofNullable(financeRepo.getTotalIncome())
                .orElse(BigDecimal.ZERO);

        BigDecimal totalExpense = Optional.ofNullable(financeRepo.getTotalExpense())
                .orElse(BigDecimal.ZERO);

        // Calculate net balance
        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        // Build category-wise totals
        Map<String, BigDecimal> categoryMap = new HashMap<>();
        List<Object[]> categoryData = financeRepo.getCategoryWiseTotals();

        for (Object[] obj : categoryData) {
            String category = (String) obj[0];
            BigDecimal amount = (BigDecimal) obj[1];

            // Handle null category values to avoid JSON issues
            if (category == null) {
                category = "UNCATEGORIZED";
            }

            categoryMap.put(category, amount);
        }

        // Fetch latest 5 transactions
        List<FinancialRecord> recent = financeRepo.findTop5ByIsDeletedFalseOrderByDateDesc();

        // Build monthly trends
        List<MonthlyTrend> trends = new ArrayList<>();
        List<Object[]> trendData = financeRepo.getMonthlyTrends();

        for (Object[] obj : trendData) {

            // PostgreSQL may return month as Double/BigDecimal
            Integer month = ((Number) obj[0]).intValue();

            BigDecimal income = (BigDecimal) obj[1];
            BigDecimal expense = (BigDecimal) obj[2];

            trends.add(new MonthlyTrend(month, income, expense));
        }

        // Build and return response
        return DashboardResponse.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .netBalance(netBalance)
                .categoryWiseTotals(categoryMap)
                .recentTransactions(recent)
                .monthlyTrends(trends)
                .build();
    }
}