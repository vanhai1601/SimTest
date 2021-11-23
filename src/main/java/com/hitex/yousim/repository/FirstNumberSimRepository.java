package com.hitex.yousim.repository;

import com.hitex.yousim.model.FirstNumberSim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FirstNumberSimRepository extends JpaRepository<FirstNumberSim,Integer> {
    @Query(value = "select f from FirstNumberSim f where (-1 = ?1 or f.mobileCarrierId = ?1)")
    List<FirstNumberSim> findFirstNumberSimByMobileCarrier(int mobileCarrierId);
}
