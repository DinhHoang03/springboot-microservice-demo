package com.example.user_service.resources.responses;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
