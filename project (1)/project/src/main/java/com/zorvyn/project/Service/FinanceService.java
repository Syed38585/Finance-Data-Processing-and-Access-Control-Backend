package com.zorvyn.project.Service;

import com.zorvyn.project.Dto.FinanceUpdateDto;
import com.zorvyn.project.Dto.FinancialRecordDto;
import com.zorvyn.project.Model.FinancialRecord;
import com.zorvyn.project.Model.User;
import com.zorvyn.project.Repository.FinanceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private final FinanceRepo financeRepo;
    private final UserService userService;

    /**
     * Retrieves all financial records that are not deleted.
     */
    public List<FinancialRecord> getAllRecords() {
        return financeRepo.findByIsDeletedFalse();
    }

    /**
     * Retrieves a financial record by ID.
     * Throws an exception if the record does not exist.
     */
    public FinancialRecord getById(Long id) {
        return financeRepo.findById(id)
                .filter(record -> !record.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("Record not found with id: " + id));
    }

    /**
     * Creates a new financial record.
     * Associates the record with a user.
     */
    public String createRecord(FinancialRecordDto dto) {

        User user = userService.getById(dto.getCreatedBy());

        FinancialRecord record = new FinancialRecord();
        record.setUser(user);
        record.setAmount(dto.getAmount());
        record.setDescription(dto.getDescription());
        record.setCategory(dto.getCategory());
        record.setDate(dto.getDate());
        record.setType(dto.getType());
        record.setDeleted(false);

        financeRepo.save(record);

        return "Record successfully created";
    }

    /**
     * Soft deletes a financial record.
     * Marks the record as deleted instead of removing it from the database.
     */
    public String delete(Long id) {
        FinancialRecord record = getById(id);
        record.setDeleted(true);
        financeRepo.save(record);

        return "Record successfully deleted";
    }

    /**
     * Updates an existing financial record.
     * Only updates fields that are provided in the request.
     */
    public String update(Long id, FinanceUpdateDto dto) {

        FinancialRecord record = getById(id);

        if (dto.getAmount() != null) {
            record.setAmount(dto.getAmount());
        }

        if (dto.getDescription() != null) {
            record.setDescription(dto.getDescription());
        }

        if (dto.getCategory() != null) {
            record.setCategory(dto.getCategory());
        }

        if (dto.getDate() != null) {
            record.setDate(dto.getDate());
        }

        if (dto.getType() != null) {
            record.setType(dto.getType());
        }

        financeRepo.save(record);

        return "Record successfully updated";
    }

    /**
     * Retrieves financial records filtered by category.
     */
    public List<FinancialRecord> getByCategory(String category) {
        return financeRepo.findByKeyword(category);
    }
}