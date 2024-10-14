package com.service.user.Service;


import com.service.user.Model.AuthResponse;
import com.service.user.Model.UserLoginRegisterDTO;


public interface AuthService {
    AuthResponse login(UserLoginRegisterDTO request);
    AuthResponse register(UserLoginRegisterDTO request);
}
