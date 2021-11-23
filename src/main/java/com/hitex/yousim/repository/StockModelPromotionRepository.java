package com.hitex.yousim.repository;

import com.hitex.yousim.model.StockModelPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockModelPromotionRepository extends JpaRepository<StockModelPromotion, Integer> {
    @Query(value = "select stp from StockModelPromotion stp where stp.stockModelId = ?1")
    StockModelPromotion findbyStockModelPromotionId(int stockModelId);
}
