package com.hitex.yousim.repository;

import com.hitex.yousim.model.AboutUsLanguage;
import com.hitex.yousim.model.view.ViewAboutUsLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutUsAndLanguageRepository extends JpaRepository<ViewAboutUsLanguage, String> {
    @Query(value = "select c from ViewAboutUsLanguage c where c.type =?1 and c.language=?2")
    List<ViewAboutUsLanguage> getAboutUsByType(int type, String code);

    @Query(value = "select c from ViewAboutUsLanguage c where c.language=?1")
    List<ViewAboutUsLanguage> findAllList(String language);

    @Query(value = "select c from ViewAboutUsLanguage c where c.type=?1 ")
    AboutUsLanguage findAboutUsById(int type);
}
