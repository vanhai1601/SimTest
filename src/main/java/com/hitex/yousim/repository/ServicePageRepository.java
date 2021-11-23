package com.hitex.yousim.repository;

import com.hitex.yousim.model.ServicePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ServicePageRepository extends JpaRepository<ServicePage, Integer> {
    @Query("select s from ServicePage s where s.serviceId = ?1")
    ServicePage getServicePageById(int serviceId);


}
