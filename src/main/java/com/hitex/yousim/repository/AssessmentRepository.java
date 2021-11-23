package com.hitex.yousim.repository;

import com.hitex.yousim.model.Assessment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
    @Query(value = "select a from Assessment a order by a.createTime DESC")
    List<Assessment> findAllAssessment(Pageable pageable);

    @Query(value = "select count(a) from Assessment a")
    int countAlltAssessment();
}
