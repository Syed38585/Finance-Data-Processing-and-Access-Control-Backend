package com.zorvyn.project.Dto;

import com.zorvyn.project.Enums.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinanceUpdateDto {

    @Positive
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    Type type;

    String category;

    LocalDate date;

    String description;

    @NotNull
    Long createdBy;
}
