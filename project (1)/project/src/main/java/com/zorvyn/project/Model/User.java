package com.zorvyn.project.Model;

import com.zorvyn.project.Enums.Role;
import com.zorvyn.project.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;

@Data
@Entity(name="users")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    String userName;

    @NonNull
    @Email
    @Column(nullable=false,unique = true)
    String email;

    @NonNull
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Enumerated(EnumType.STRING)
    Status status;

    @Value("${boolean.isDeleted}")
    boolean isDeleted;

    @CreationTimestamp
    @Column(nullable=false,updatable=false)
    Timestamp created;
}
