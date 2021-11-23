package com.hitex.yousim.repository;

import com.hitex.yousim.model.PaymentCheckout;
import com.hitex.yousim.model.PaymentCheckoutHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMentCheckoutHisRepository extends JpaRepository<PaymentCheckoutHis, Long> {
    PaymentCheckout getByOrderCode(String orderCode);
}
