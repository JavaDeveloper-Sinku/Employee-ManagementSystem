package com.example.Employee.Management.service;

import com.example.Employee.Management.entity.RefreshToken;
import com.example.Employee.Management.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);

    void deleteRefreshToken(User user);
}
