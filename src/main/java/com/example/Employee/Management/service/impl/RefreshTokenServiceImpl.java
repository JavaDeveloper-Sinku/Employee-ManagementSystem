package com.example.Employee.Management.service.impl;

import com.example.Employee.Management.entity.RefreshToken;
import com.example.Employee.Management.entity.User;
import com.example.Employee.Management.repository.RefreshTokenRepository;
import com.example.Employee.Management.service.RefreshTokenService;

import java.sql.Ref;
import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshTokenServiceImpl implements RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;


    private static final long REFRESH_TOKEN_EXPIRY_DAYS = 7;

    @Override
    public RefreshToken createRefreshToken(User user) {

        refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(
                        LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRY_DAYS)
                )
                .revoked(false)
                .build();

        return  refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh token not found"));

        if (refreshToken.isRevoked()){
            throw new RuntimeException("Refresh token has been revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())){

            refreshTokenRepository.delete(refreshToken);

            throw new RuntimeException("Refresh token has expired");
        }

        return refreshToken;
    }

    @Override
    public void deleteRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);

    }
}
