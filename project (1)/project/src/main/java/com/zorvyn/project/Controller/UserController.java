package com.zorvyn.project.Controller;

import com.zorvyn.project.Dto.UpdateDto;
import com.zorvyn.project.Dto.UserDto;
import com.zorvyn.project.Enums.Status;
import com.zorvyn.project.Model.User;
import com.zorvyn.project.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final UserService userService;

    /**
     * Retrieves all users with pagination support.
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 200 with paginated user data.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable) {
        List<User> users = userService.getAll(pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by ID.
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 200 with the user details.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Creates a new user.
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 201 when the user is successfully created.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        String response = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates the status of a user (e.g., ACTIVE/INACTIVE).
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 200 after successful update.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> patchStatus(@PathVariable Long id,
                                              @RequestParam Status status) {
        String response = userService.updateStatus(id, status);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates user details.
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 200 after successful update.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody UpdateDto updateDto) {
        String response = userService.updateUser(id, updateDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a user (soft delete).
     *
     * Accessible only by ADMIN role.
     * Returns HTTP 200 after successful deletion.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = userService.delete(id);
        return ResponseEntity.ok(response);
    }
}