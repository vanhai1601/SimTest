package com.hitex.yousim.repository;

import com.hitex.yousim.model.SimpackageLanguage;
import com.hitex.yousim.model.view.ViewSimPackage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimPackageRepository extends JpaRepository<ViewSimPackage, Integer> {
    @Query(value = "select vsp from ViewSimPackage vsp where vsp.codeLanguage = ?1 order by vsp.promotion DESC, vsp.createTime DESC")
    List<ViewSimPackage> findSimPackagePromotion(String codeLanguage,Pageable pageable);

    @Query(value = "select count(vsp) from ViewSimPackage vsp where vsp.codeLanguage = ?1")
    int countSimPackagePromotion(String codeLanguage);

    @Query(value = "select vsp from ViewSimPackage vsp where vsp.packageId = ?1 and vsp.codeLanguage = ?2")
    ViewSimPackage findSimPackageById(int id, String codeLanguage);

    @Query(value = "select vsp from ViewSimPackage vsp where vsp.codeLanguage = ?1 and vsp.mobileCarrierId =?2")
    List<ViewSimPackage> findSimPackageByMobileCarrier(String codeLanguage, int mobileCarrierId);
    @Query(value = "select vsp from ViewSimPackage  vsp where vsp.codeLanguage = ?1  and vsp.packageCode = ?2")
    ViewSimPackage findSimPackageByPackageCode(String codeLanguage,String packageCode);
}
