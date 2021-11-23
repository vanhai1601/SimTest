package com.hitex.yousim.repository;

import com.hitex.yousim.model.StockModelPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockModelPriceRepository extends JpaRepository<StockModelPrice, Integer> {
    @Query(value = "select smp from StockModelPrice smp where smp.stockModelId = ?1")
    StockModelPrice findSmpByStockModelId(int stockModelId);
}
