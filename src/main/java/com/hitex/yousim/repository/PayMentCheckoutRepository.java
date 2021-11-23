package com.hitex.yousim.repository;

import com.hitex.yousim.model.PaymentCheckout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PayMentCheckoutRepository extends JpaRepository<PaymentCheckout,Long> {
    @Query(value = "select pc from PaymentCheckout pc where pc.orderCode = ?1")
    PaymentCheckout getByOrderCode(String orderCode);

    @Query(value = "select pc from PaymentCheckout pc where (pc.orderCode = ?1 or pc.partnerOrderCode = ?1) " +
            "and pc.transStatus = '00'")
    PaymentCheckout getConfirmByCode(String orderCode);

    @Query(value = "select pc from PaymentCheckout pc where (pc.orderCode = ?1 or pc.partnerOrderCode = ?1) " +
            "and pc.transStatus = '01'")
    PaymentCheckout getWaitingByCode(String orderCode);


}
