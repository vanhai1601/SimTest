package com.hitex.yousim.repository;

import com.hitex.yousim.dto.response.newsLanguage.ListNewsLangResponse;
import com.hitex.yousim.dto.response.newsLanguage.newsLanguageResponse;
import com.hitex.yousim.model.NewsLanguage;
import com.hitex.yousim.model.view.KitAndIsdn;
import com.hitex.yousim.model.view.vNewsLanguage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface vnewsLanguageRepository extends JpaRepository<vNewsLanguage,Integer> {
    @Query(value = "select vnl from vNewsLanguage vnl where vnl.newsId=?1 and vnl.language=?2 ")
    vNewsLanguage findByIdAndLanguage(Integer newsId, String language);
    @Query(value = "select vnl from vNewsLanguage vnl where vnl.language=?1")
    List<vNewsLanguage> findAlls(String language);
    @Query(value = "select vnl from vNewsLanguage vnl where vnl.newsId=?1 and vnl.language=?2 ")
    vNewsLanguage findByIdByNewidByLang(Integer newId,String language);

    @Query(value = "select n from vNewsLanguage n where n.language=?1 order by n.createTime")
    List<vNewsLanguage> findByDate(String language);

    @Query(value = "select vnl from vNewsLanguage vnl")
    List<vNewsLanguage> findAllByPage(Pageable pageable);

    @Query(value = "select count(vnl.id) from vNewsLanguage vnl ")
    int countNews();
}
