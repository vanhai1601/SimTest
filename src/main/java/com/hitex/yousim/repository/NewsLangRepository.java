package com.hitex.yousim.repository;

import com.hitex.yousim.model.News;
import com.hitex.yousim.model.NewsLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsLangRepository  extends JpaRepository<NewsLanguage,Integer> {
    @Query(value = "select nl from NewsLanguage nl where nl.newsId=?1 and nl.language=?2")
    NewsLanguage findByIdAndAndLanguage( Integer newsId,String language);

}
