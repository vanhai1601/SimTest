package com.hitex.yousim.repository;

import com.hitex.yousim.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<Village, String> {
	List<Village> findByDistrictId(String districtId);

	@Query(value = "select d from Village d where d.villageId = ?1")
	Village findVillageById(String id);
}
