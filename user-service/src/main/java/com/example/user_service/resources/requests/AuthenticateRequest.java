package com.example.user_service.resources.requests;

public record AuthenticateRequest(
        String username,
        String password
) { }
