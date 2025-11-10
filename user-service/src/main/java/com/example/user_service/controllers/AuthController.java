package com.example.user_service.controllers;

import com.example.user_service.resources.requests.AuthenticateRequest;
import com.example.user_service.resources.responses.AuthResponse;
import com.example.user_service.services.interfaces.IAuthService;
import com.nimbusds.jose.JOSEException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login (@RequestBody AuthenticateRequest request) {
        var response = authService.authentication(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    ResponseEntity<AuthResponse> refreshToken(@RequestParam String rt) throws ParseException, JOSEException {
        var response = authService.refreshToken(rt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestParam String rt) throws ParseException, JOSEException {
        authService.logout(rt);
        return ResponseEntity.ok("Log out successfully");
    }
}
