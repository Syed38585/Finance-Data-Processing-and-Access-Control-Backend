package com.zorvyn.project;


import com.zorvyn.project.Model.FinancialRecord;
import com.zorvyn.project.Repository.FinanceRepo;
import com.zorvyn.project.Service.FinanceService;
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
class FinanceServiceTest {

    @Mock
    private FinanceRepo financeRepo;

    @InjectMocks
    private FinanceService financeService;

    @Test
    void shouldReturnAllRecords() {

        FinancialRecord record = new FinancialRecord();
        record.setAmount(BigDecimal.valueOf(1000));

        when(financeRepo.findByIsDeletedFalse())
                .thenReturn(List.of(record));

        List<FinancialRecord> result = financeService.getAllRecords();

        assertEquals(1, result.size());
        assertEquals(BigDecimal.valueOf(1000), result.get(0).getAmount());
    }

    @Test
    void shouldDeleteRecord() {

        FinancialRecord record = new FinancialRecord();
        record.setDeleted(false);

        when(financeRepo.findById(1L))
                .thenReturn(java.util.Optional.of(record));

        String response = financeService.delete(1L);

        assertTrue(record.isDeleted());
        verify(financeRepo).save(record);
        assertEquals("Record successfully deleted", response);
    }
}