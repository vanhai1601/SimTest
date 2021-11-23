package com.hitex.yousim.repository;

import com.hitex.yousim.model.MobileCarrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MobileCarrierRepository extends JpaRepository<MobileCarrier,Integer> {
    @Query(value = "select m from MobileCarrier m where (-1 = ?1 or m.nationId = ?1)")
    List<MobileCarrier> findMobileCarrierByNationId(int nationId);
}
