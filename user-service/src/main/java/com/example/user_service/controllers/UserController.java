package com.example.user_service.controllers;

import com.example.user_service.resources.requests.UserRequest;
import com.example.user_service.resources.responses.UserResponse;
import com.example.user_service.services.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        var result = userService.createAccount(request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteAccount(id);
        return ResponseEntity.ok("Delete user successfully");
    }
}
