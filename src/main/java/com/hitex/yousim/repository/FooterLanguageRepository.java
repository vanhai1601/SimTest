package com.hitex.yousim.repository;

import com.hitex.yousim.model.FooterLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FooterLanguageRepository extends JpaRepository<FooterLanguage, Integer> {
    @Query("select fl from FooterLanguage fl where  fl.codeLanguage = ?1")
    FooterLanguage getFooter( String codeLanguage);

    @Query("select fl from FooterLanguage fl where fl.footerId =?1 and fl.codeLanguage = ?2")
    FooterLanguage getFooterLanguageByFooterId(int footerId, String codeLanguage);
}
