package com.hitex.yousim.repository;

import com.hitex.yousim.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Query(value = "select q from Question q where  q.id = ?1")
    Question findById(int id);
}
