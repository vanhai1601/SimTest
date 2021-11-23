package com.hitex.yousim.repository;

import com.hitex.yousim.model.ServicePageLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicePageLanguageRepository extends JpaRepository<ServicePageLanguage, Integer> {
    @Query("select sl from ServicePageLanguage sl where sl.codeLanguage = ?1")
    List<ServicePageLanguage> getService(String codeLanguage);
}
