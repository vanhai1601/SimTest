package com.hitex.yousim.repository;

import com.hitex.yousim.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    @Query(value = "select c from City c where c.cityId = ?1")
    City findCityById(String id);
}
