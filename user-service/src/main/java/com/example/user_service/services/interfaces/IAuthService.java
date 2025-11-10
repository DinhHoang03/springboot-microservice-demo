package com.example.user_service.services.interfaces;

import com.example.user_service.resources.requests.AuthenticateRequest;
import com.example.user_service.resources.responses.AuthResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthService {
    AuthResponse authentication(AuthenticateRequest request);
    AuthResponse refreshToken(String rt) throws ParseException, JOSEException;
    void logout(String rt) throws ParseException, JOSEException;
}
