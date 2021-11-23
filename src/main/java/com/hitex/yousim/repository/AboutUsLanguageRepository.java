package com.hitex.yousim.repository;

import com.hitex.yousim.model.AboutUsLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutUsLanguageRepository extends JpaRepository<AboutUsLanguage, String> {
    @Query(value = "select c from AboutUsLanguage c where c.idAboutUs=?1 and c.language=?2")
    AboutUsLanguage findAboutUsById(int idAboutUs, String language);
    @Query(value = "select c from AboutUsLanguage c where c.idAboutUs=?1 and c.language=?2 ")
    AboutUsLanguage findAboutUsLanguageByIdAboutUs(int idAboutUs, String language);
}
