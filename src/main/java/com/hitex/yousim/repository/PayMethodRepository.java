package com.hitex.yousim.repository;

import com.hitex.yousim.model.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Integer> {
}
