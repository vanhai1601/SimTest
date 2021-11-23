package com.hitex.yousim.repository;

import com.hitex.yousim.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
	List<District> findByCityId(String cityId);

	@Query(value = "select d from District d where d.districtId = ?1")
	District findDistrictById(String id);
}
