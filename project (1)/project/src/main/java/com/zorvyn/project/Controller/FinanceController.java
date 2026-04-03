package com.zorvyn.project.Controller;

import com.zorvyn.project.Dto.FinanceUpdateDto;
import com.zorvyn.project.Dto.FinancialRecordDto;
import com.zorvyn.project.Model.FinancialRecord;
import com.zorvyn.project.Service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController implements IFinancialController {

    private final FinanceService financeService;

    /**
     * Retrieves all financial records.
     *
     * Accessible by VIEWER, ANALYST, and ADMIN roles.
     * Returns HTTP 200 with the list of records.
     */
    @PreAuthorize("hasAnyRole('VIEWER','ANALYST','ADMIN')")
    @GetMapping("/records")
    public ResponseEntity<List<FinancialRecord>> getFinancialRecords() {
        List<FinancialRecord> records = financeService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    /**
     * Retrieves a specific financial record by its ID.
     *
     * Accessible by VIEWER, ANALYST, and ADMIN roles.
     * Returns HTTP 200 with the requested record.
     */
    @PreAuthorize("hasAnyRole('VIEWER','ANALYST','ADMIN')")
    @GetMapping("/records/{id}")
    public ResponseEntity<FinancialRecord> getById(@PathVariable Long id) {
        FinancialRecord record = financeService.getById(id);
        return ResponseEntity.ok(record);
    }

    /**
     * Creates a new financial record.
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 201 when the record is successfully created.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/records")
    public ResponseEntity<String> createFinancialRecord(@RequestBody FinancialRecordDto financialRecord) {
        String response = financeService.createRecord(financialRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates an existing financial record
     *
     * Accessible only by ADMIN role
     * Returns HTTP 200 after successful update
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/records/{id}")
    public ResponseEntity<String> updateFinancialRecord(@PathVariable Long id,
                                                        @RequestBody FinanceUpdateDto updateDto) {
        String response = financeService.update(id, updateDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a financial record (soft delete).
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 200 after successful deletion.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/records/{id}")
    public ResponseEntity<String> deleteFinancialRecord(@PathVariable Long id) {
        String response = financeService.delete(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves financial records filtered by category.
     *
     * Accessible by VIEWER, ANALYST, and ADMIN roles.
     * Returns HTTP 200 with filtered results.
     */
    @PreAuthorize("hasAnyRole('VIEWER','ANALYST','ADMIN')")
    @GetMapping("/records/by-category")
    public ResponseEntity<List<FinancialRecord>> getByCategory(@RequestParam String category) {
        List<FinancialRecord> records = financeService.getByCategory(category);
        return ResponseEntity.ok(records);
    }
}