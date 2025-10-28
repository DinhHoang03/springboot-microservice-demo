package com.example.user_service.services.interfaces;

import com.example.user_service.resources.requests.UserRequest;
import com.example.user_service.resources.responses.UserResponse;

public interface IUserService {
    UserResponse createAccount(UserRequest request);
    void deleteAccount(Long id);
}
