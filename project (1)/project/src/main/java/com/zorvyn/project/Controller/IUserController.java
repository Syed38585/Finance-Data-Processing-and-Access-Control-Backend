package com.zorvyn.project.Controller;

import com.zorvyn.project.Dto.UpdateDto;
import com.zorvyn.project.Dto.UserDto;
import com.zorvyn.project.Enums.Status;
import com.zorvyn.project.Model.User;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface IUserController {

    public ResponseEntity<List<User>> getAllUsers(Pageable pageable);

    public ResponseEntity<User> getById(Long id);

    public ResponseEntity<String> createUser(UserDto userDto);

    public ResponseEntity<String> updateUser(Long id, UpdateDto updateDto);

    public ResponseEntity<String> patchStatus(Long id, Status status);

    public ResponseEntity<String> deleteUser(Long id);
}
