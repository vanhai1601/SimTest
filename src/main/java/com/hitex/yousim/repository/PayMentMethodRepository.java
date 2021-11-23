package com.hitex.yousim.repository;

import com.hitex.yousim.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMentMethodRepository extends JpaRepository<PaymentMethod,String> {
    @Query(value = "select pm from PaymentMethod pm where pm.status = 1 and pm.methodCode = ?1")
    PaymentMethod findByMedthodCode(String methodCode);
}
