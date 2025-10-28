package com.example.user_service.resources.requests;

import java.util.Set;

public record UserRequest(
        String username,
        String email,
        String password,
        Set<Long> roles
) {
}
