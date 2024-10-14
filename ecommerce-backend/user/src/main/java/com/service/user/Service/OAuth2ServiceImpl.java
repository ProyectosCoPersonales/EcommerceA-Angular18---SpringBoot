package com.service.user.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.service.user.Jwt.JwtService;
import com.service.user.Model.AuthResponse;
import com.service.user.Model.User;
import com.service.user.Model.UserLoginRegisterDTO;


@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponse processOAuth2User(OAuth2AuthenticationToken authenticationToken) {
        OAuth2User oAuth2User = authenticationToken.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email not provided by OAuth2 provider");
        }

        User existingUser = userService.findByEmail(email);

        if (existingUser != null) {
            if (existingUser.getPassword() != null && !existingUser.getPassword().isEmpty()) {
                throw new IllegalStateException("User already registered. Please log in with credentials.");
            }
            String token = jwtService.getToken(existingUser);
            return new AuthResponse(token);
        }

        UserLoginRegisterDTO register = new UserLoginRegisterDTO();
        register.setEmail(email);

        AuthResponse token = authService.register(register);

        return token;
    }
}

