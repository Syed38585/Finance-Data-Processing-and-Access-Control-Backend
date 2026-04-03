package com.zorvyn.project.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {
    @NonNull
    String name;
    @NonNull
    String email;
    @NonNull
    String password;
}
