package com.zorvyn.project.Service;

import com.zorvyn.project.Dto.UpdateDto;
import com.zorvyn.project.Dto.UserDto;
import com.zorvyn.project.Enums.Role;
import com.zorvyn.project.Enums.Status;
import com.zorvyn.project.Model.User;
import com.zorvyn.project.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * Retrieves all users with pagination.
     * Only non-deleted users are returned.
     */
    public List<User> getAll(Pageable pageable) {
        return userRepo.findByIsDeletedFalse(pageable);
    }

    /**
     * Retrieves a user by ID.
     * Throws an exception if the user is not found or is deleted.
     */
    public User getById(Long id) {
        return userRepo.findById(id)
                .filter(user -> !user.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    /**
     * Updates the status of a user (ACTIVE / INACTIVE).
     */
    public String updateStatus(Long id, Status status) {
        User user = getById(id);
        user.setStatus(status);
        userRepo.save(user);

        return "User status updated successfully";
    }

    /**
     * Soft deletes a user.
     * Marks the user as deleted instead of removing from database.
     */
    public String delete(Long id) {
        User user = getById(id);
        user.setDeleted(true);
        userRepo.save(user);

        return "User deleted successfully with id: " + id;
    }

    /**
     * Creates a new user.
     * Password is encoded before saving.
     */
    public String createUser(UserDto userDto) {

        if (userRepo.findByEmailIgnoreCase(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists with this email");
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // 🔐 important
        user.setUserName(userDto.getName());
        user.setRole(Role.USER); // or VIEWER based on your system
        user.setStatus(Status.ACTIVE);
        user.setDeleted(false);

        userRepo.save(user);

        return "User created successfully";
    }

    /**
     * Updates user details.
     * Only fields provided in request are updated.
     */
    public String updateUser(Long id, UpdateDto updateDto) {

        User user = getById(id);

        if (updateDto.getEmail() != null) {
            user.setEmail(updateDto.getEmail());
        }

        if (updateDto.getName() != null) {
            user.setUserName(updateDto.getName());
        }

        if (updateDto.getRole() != null) {
            user.setRole(updateDto.getRole());
        }

        userRepo.save(user);

        return "User updated successfully";
    }
}