package com.hitex.yousim.repository;

import com.hitex.yousim.model.KIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface KitRepository extends JpaRepository<KIT, Integer> {
    @Query(value = "select k from KIT k where k.isdn = ?1")
    KIT findKitByIsdn(String isdn);
}
