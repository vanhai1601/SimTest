package com.hitex.yousim.repository;

import com.hitex.yousim.model.AboutUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutUsRepository extends JpaRepository<AboutUs, String> {

    @Query(value = "select c from AboutUs c where c.partner=?1 ")
    AboutUs findPartnerByName(String partner);

    @Query(value = "select c from AboutUs c where c.type=4 ")
    List<AboutUs> findPartnerByType();

    @Query(value = "select c from AboutUs c where c.id=?1 ")
    AboutUs findAboutUsById(int id);
}
