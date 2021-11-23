package com.hitex.yousim.repository;

import com.hitex.yousim.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockModelRepository extends JpaRepository<StockModel, Integer> {
    @Query(value = "select sm from StockModel sm where sm.stockModelId = ?1")
    StockModel findStockModelById(int stockModelId);
}
