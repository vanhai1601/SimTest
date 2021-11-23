package com.hitex.yousim.repository;

import com.hitex.yousim.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
    @Query(value = "select op from OrderProduct op where op.orderCode = ?1")
    OrderProduct findByOrderCode(String orderCode);
}
