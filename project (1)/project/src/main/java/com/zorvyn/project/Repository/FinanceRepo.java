package com.zorvyn.project.Repository;

import com.zorvyn.project.Model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FinanceRepo extends JpaRepository<FinancialRecord, Long> {

    @Query("""
    SELECT f FROM FinancialRecord f
    WHERE f.isDeleted = false AND (
        LOWER(f.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(f.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(f.type) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        CAST(f.date AS string) LIKE CONCAT('%', :keyword, '%')
    )
""")
    //find by category for efficient searching
    List<FinancialRecord> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = 'INCOME' AND f.isDeleted = false")
    BigDecimal getTotalIncome();

    // Total Expense
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = 'EXPENSE' AND f.isDeleted = false")
    BigDecimal getTotalExpense();

    // Category-wise totals
    @Query("""
        SELECT f.category, SUM(f.amount)
        FROM FinancialRecord f
        WHERE f.isDeleted = false
        GROUP BY f.category
    """)
    List<Object[]> getCategoryWiseTotals();

    // Recent transactions
    List<FinancialRecord> findTop5ByIsDeletedFalseOrderByDateDesc();

    // Monthly trends
    @Query("""
    SELECT EXTRACT(MONTH FROM f.date),
           SUM(CASE WHEN f.type = 'INCOME' THEN f.amount ELSE 0 END),
           SUM(CASE WHEN f.type = 'EXPENSE' THEN f.amount ELSE 0 END)
    FROM FinancialRecord f
    WHERE f.isDeleted = false
    GROUP BY EXTRACT(MONTH FROM f.date)
    ORDER BY EXTRACT(MONTH FROM f.date)
""")
    List<Object[]> getMonthlyTrends();

    List<FinancialRecord> findByCategoryAndIsDeletedFalse(String category);

    List<FinancialRecord> findByIsDeletedFalse();
}