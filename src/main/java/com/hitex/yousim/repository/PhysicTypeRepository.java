package com.hitex.yousim.repository;

import com.hitex.yousim.model.PhysicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicTypeRepository extends JpaRepository<PhysicType,Integer> {
    @Query(value = "select p from PhysicType p where p.codeLanguage = ?1")
    List<PhysicType> findAll(String codelanguage);
}
