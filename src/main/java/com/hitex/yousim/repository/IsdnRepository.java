package com.hitex.yousim.repository;

import com.hitex.yousim.model.Isdn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IsdnRepository extends JpaRepository<Isdn, Integer> {
    @Query(value = "select i from Isdn i where i.isdn = ?1")
    Isdn findIsdnByIsdn(String isdn);
}
