package com.stock.tracker.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.tracker.dto.UserDTO;
import com.stock.tracker.enums.UserRole;
import com.stock.tracker.request.CreateUserRequest;
import com.stock.tracker.request.UpdateUserRequest;
import com.stock.tracker.service.UserService;
import com.stock.tracker.util.ResponseHandler;

@RestController
@RequestMapping("/api/public/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseHandler.successResponse(HttpStatus.OK, "Users retrieved successfully", users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID id) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "User retrieved successfully", user);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();
        String plainTextPassword = createUserRequest.getPlainTextPassword();
        UserRole role = createUserRequest.getRole();

        if (username == null || plainTextPassword == null || role == null) {
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Username, password, and role are required");
        }

        UserDTO createdUser = userService.createUser(username, plainTextPassword, role);
        if (createdUser != null) {
            return ResponseHandler.successResponse(HttpStatus.CREATED, "User created successfully", createdUser);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User creation failed");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest updateUserRequest) {
        String username = updateUserRequest.getUsername();
        String plainTextPassword = updateUserRequest.getPlainTextPassword();
        UserRole role = updateUserRequest.getRole();

        if (username == null && plainTextPassword == null && role == null) {
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "No update fields provided");
        }

        UserDTO updatedUser = userService.updateUser(id, username, plainTextPassword, role);
        if (updatedUser != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "User updated successfully", updatedUser);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        boolean result = userService.deleteUser(id);
        if (result) {
            return ResponseHandler.successResponse(HttpStatus.OK, "User deleted successfully", null);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}