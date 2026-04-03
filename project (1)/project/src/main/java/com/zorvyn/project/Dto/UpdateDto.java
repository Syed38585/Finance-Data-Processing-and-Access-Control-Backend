package com.zorvyn.project.Dto;

import com.zorvyn.project.Enums.Role;
import lombok.Data;

@Data
public class UpdateDto {
    String name;
    String email;
    Role role;
}
