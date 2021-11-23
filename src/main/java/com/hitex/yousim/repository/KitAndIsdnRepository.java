package com.hitex.yousim.repository;

import com.hitex.yousim.model.view.KitAndIsdn;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitAndIsdnRepository extends JpaRepository<KitAndIsdn,String> {
    @Query(value = "select k from KitAndIsdn k where  k.stockModelId = ?1 and k.status = 1")
    List<KitAndIsdn> findByStockModelId(int stockModelId,Pageable pageable);

    @Query(value = "select count(k) from KitAndIsdn k where  k.stockModelId = ?1 and k.status = 1")
    int countKitAndIsdnByStockModelId(int stockModelId);

    @Query(value = "select k from KitAndIsdn k where k.stockModelId = ?1 and k.status = 1")
    List<KitAndIsdn> findKitAndIsdnByType(int stockModelId);

    @Query(value = "select k from KitAndIsdn k where k.stockModelId = ?1 and k.status = 1 order by k.promotion DESC, k.createTime DESC ")
    List<KitAndIsdn> findKitAndIsdnByPromotion(int stockModelID,Pageable pageable);

    @Query(value = "select count(k) from KitAndIsdn k where k.stockModelId = ?1 and k.status = 1")
    int conutKitAndIsdnByPromotion(int stockModelId);

    @Query(value = "select k from KitAndIsdn k where k.isdn = ?1")
    KitAndIsdn findKitAndIsdnByIsdn(String isdn);
}
