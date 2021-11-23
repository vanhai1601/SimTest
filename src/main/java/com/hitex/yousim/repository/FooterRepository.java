package com.hitex.yousim.repository;

import com.hitex.yousim.model.Footer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface FooterRepository extends JpaRepository<Footer, Integer> {

    @Query(value = "select f from Footer f where f.footerId = ?1")
    Footer getFooterById(int footerId);
}
