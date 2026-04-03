package com.zorvyn.project.Model;

import com.zorvyn.project.Enums.Type;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
public class FinancialRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    Type type;

    String category;

    LocalDate date;

    String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Value("${boolean.isDeleted}")
    boolean isDeleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    Timestamp createdAt;
}
