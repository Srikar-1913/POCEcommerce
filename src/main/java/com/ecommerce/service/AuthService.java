package com.ecommerce.service;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.LoginResponse;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.entity.User;

public interface AuthService {

    User register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}