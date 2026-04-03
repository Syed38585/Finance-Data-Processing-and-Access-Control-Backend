package com.zorvyn.project.Dto;


import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull
        String userName,
        @NotNull
        String email,
        @NotNull
        String password
) {
}
