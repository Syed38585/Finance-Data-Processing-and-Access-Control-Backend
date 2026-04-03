package com.zorvyn.project.Dto;

import com.zorvyn.project.Enums.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinancialRecordDto {

    @Positive
    @NotNull
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    Type type;

    @NotNull
    String category;

    @DateTimeFormat
    LocalDate date;

    String description;

    @NotNull
    Long createdBy;
}
