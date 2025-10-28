package com.example.user_service.services.imps;

import com.example.user_service.config.SecurityConfig;
import com.example.user_service.exception.code.AppCode;
import com.example.user_service.exception.custom.AppException;
import com.example.user_service.models.entities.Role;
import com.example.user_service.models.entities.User;
import com.example.user_service.models.repositories.UserRepository;
import com.example.user_service.resources.requests.UserRequest;
import com.example.user_service.resources.responses.UserResponse;
import com.example.user_service.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final SecurityConfig securityConfig;
    private final UserRepository userRepository;

    public UserService(SecurityConfig securityConfig, UserRepository userRepository) {
        this.securityConfig = securityConfig;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createAccount(UserRequest request) {
        if (request == null) throw new AppException(AppCode.REQUEST_NULL);

        if (userRepository.existsByUsername(request.username()) || userRepository.existsByEmail(request.email()))
            throw new AppException(AppCode.USER_EXIST);

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());

        String encryptedPassword = securityConfig.passwordEncoder().encode(request.password());
        user.setPassword(encryptedPassword);

        User result = userRepository.save(user);

        return new UserResponse(result.getId(), result.getUsername(), result.getEmail(), result.getPassword(), result.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    }

    @Override
    public void deleteAccount(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(AppCode.USER_NOT_EXIST));
        userRepository.delete(user);
    }
}
