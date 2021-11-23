package com.hitex.yousim.repository;

import com.hitex.yousim.model.ReturnVnPayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnVnpayLogRepository extends JpaRepository<ReturnVnPayLog,Long> {
    @Query("SELECT returnLog from ReturnVnPayLog returnLog " +
            "where returnLog.orderCode = :orderCode and returnLog.partnerTransStatus = 'Thành công'")
    List<ReturnVnPayLog> checkConfirm(String orderCode);
}
