package com.example.user_service.resources.responses;

import java.util.Set;

public record UserResponse(
        Long id,
        String username,
        String email,
        String password,
        Set<String> roles
) {
}
