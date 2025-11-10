package com.example.user_service.services.imps;

import com.example.user_service.exception.code.AppCode;
import com.example.user_service.exception.custom.AppException;
import com.example.user_service.models.entities.User;
import com.example.user_service.models.repositories.UserRepository;
import com.example.user_service.resources.requests.AuthenticateRequest;
import com.example.user_service.resources.responses.AuthResponse;
import com.example.user_service.services.interfaces.IAuthService;
import com.example.user_service.services.interfaces.IJwtService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class AuthService implements IAuthService {

    private final IJwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IJwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse authentication(AuthenticateRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new AppException(AppCode.USER_NOT_EXIST));

        boolean matches = passwordEncoder.matches(request.password(), user.getPassword());

        if (!matches) throw new AppException(AppCode.UNAUTHENTICATED);

        JwtService.TokenPair token = jwtService.generateTokenPair(user);

        return new AuthResponse(token.getAccessToken(), token.getRefreshToken());
    }

    @Override
    public AuthResponse refreshToken(String rt) throws ParseException, JOSEException {
        if (rt == null) throw new AppException(AppCode.UNAUTHENTICATED);

        JwtService.TokenPair tokenPair = jwtService.refreshToken(rt);
        return new AuthResponse(tokenPair.getAccessToken(), tokenPair.getRefreshToken());
    }


    @Override
    public void logout(String rt) throws ParseException, JOSEException {
        if (rt == null) throw new AppException(AppCode.UNAUTHENTICATED);
        SignedJWT token = jwtService.verifyToken(rt, "refresh");
        jwtService.invalidatedToken(token);
    }
}
