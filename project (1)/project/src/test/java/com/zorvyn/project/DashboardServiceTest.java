package com.zorvyn.project;


import com.zorvyn.project.Model.DashboardResponse;
import com.zorvyn.project.Repository.FinanceRepo;
import com.zorvyn.project.Service.DashboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private FinanceRepo financeRepo;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void shouldReturnDashboardSummary() {

        when(financeRepo.getTotalIncome()).thenReturn(BigDecimal.valueOf(5000));
        when(financeRepo.getTotalExpense()).thenReturn(BigDecimal.valueOf(2000));
        when(financeRepo.getCategoryWiseTotals()).thenReturn(List.of());
        when(financeRepo.findTop5ByIsDeletedFalseOrderByDateDesc()).thenReturn(List.of());
        when(financeRepo.getMonthlyTrends()).thenReturn(List.of());

        DashboardResponse response = dashboardService.getDashboardSummary();

        assertEquals(BigDecimal.valueOf(5000), response.getTotalIncome());
        assertEquals(BigDecimal.valueOf(2000), response.getTotalExpense());
        assertEquals(BigDecimal.valueOf(3000), response.getNetBalance());
    }
}
