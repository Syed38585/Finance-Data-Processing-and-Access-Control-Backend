package com.zorvyn.project.Controller;

import com.zorvyn.project.Dto.FinanceUpdateDto;
import com.zorvyn.project.Dto.FinancialRecordDto;
import com.zorvyn.project.Model.FinancialRecord;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFinancialController {

    public ResponseEntity<List<FinancialRecord>> getFinancialRecords();

    public ResponseEntity<FinancialRecord> getById(Long id);

    public ResponseEntity<String> createFinancialRecord(FinancialRecordDto financialRecord);

    public ResponseEntity<String> updateFinancialRecord(Long dto, FinanceUpdateDto updateDto);

    public ResponseEntity<String> deleteFinancialRecord(Long id);

    public ResponseEntity<List<FinancialRecord>> getByCategory(String category);
}
