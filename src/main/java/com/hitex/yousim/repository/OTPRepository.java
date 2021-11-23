package com.hitex.yousim.repository;

import com.hitex.yousim.model.Otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<Otp, Long> {
    @Query(value = "select o from Otp o where o.otpCode = ?1")
    Otp findOTPByOTPCode(String otpCode);
}
