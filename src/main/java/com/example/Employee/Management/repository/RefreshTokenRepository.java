package com.example.Employee.Management.repository;

import com.example.Employee.Management.entity.RefreshToken;
import com.example.Employee.Management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.beans.Transient;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query("""
       SELECT rt
       FROM RefreshToken rt
       JOIN FETCH rt.user
       WHERE rt.token = :token
       """)
    Optional<RefreshToken> findByToken(@Param("token")String token);
    Optional<RefreshToken> findByUser(User user);



    @Transient
    @Modifying
    @Query("" +
            "DELETE FROM RefreshToken rt " +
            "WHERE rt.user" +
            " = :user" +
            "")
    void deleteByUser(User user);
}
