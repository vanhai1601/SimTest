package com.hitex.yousim.repository;

import com.hitex.yousim.dto.request.news.NewsRequest;
import com.hitex.yousim.model.City;
import com.hitex.yousim.model.News;
import com.hitex.yousim.model.User;
import com.hitex.yousim.model.view.vNewsLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
    @Query(value = "select u from  User u where u.token=?1 and u.session=?2")
    User findBysessionAndToken(String token, String session);

//    @Query(value = "select n from  News n where n.id=?1")
//    News findByID(int id);
    @Query(value = "SELECT n.* FROM vNewsLanguage n WHERE n.create_time=?1 > date_sub(now(), interval 2 day)", nativeQuery = true)
    List<vNewsLanguage> findByDate(LocalDateTime localDateTime);
    @Query(value = "select vnl from vNewsLanguage vnl where vnl.newsId=?1")
    vNewsLanguage findByIdAndLanguage(Integer newsId);
}
