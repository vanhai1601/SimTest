package com.hitex.yousim.repository;

import com.hitex.yousim.model.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NationRepository extends JpaRepository<Nation, Integer> {
    @Query(value = "select n from Nation n where n.idNation = ?1")
    Nation findById(int id);
}
