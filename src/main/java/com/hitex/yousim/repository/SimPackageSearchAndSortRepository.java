package com.hitex.yousim.repository;

import com.hitex.yousim.model.view.ViewSimPackage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Repository
public class SimPackageSearchAndSortRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public SimPackageSearchAndSortRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public List<ViewSimPackage> searchAndSortSimpackage(int nationId, int mobileCarrierId, int packageId, double price,
                                                        String codeLanguage, String description,int page,int pasgeSize, String sqlEnd){
        String sql = "select vsp from ViewSimPackage  vsp where (-1 = '"+nationId+"' or vsp.nationId = '"+nationId+"') " +
                "and (-1 = '"+mobileCarrierId+"' or vsp.mobileCarrierId = '"+mobileCarrierId+"') " +
                "and (-1 = '"+packageId+"' or vsp.packageId = '"+packageId+"') and (-1 = '"+price+"' or vsp.price < '"+price+"') " +
                "and vsp.codeLanguage = '"+codeLanguage+"' and vsp.status = 1 and (-1 = '"+description+"' or vsp.description like '%"+description+"%')";
        Session session = sessionFactory.getCurrentSession();
        Query<ViewSimPackage> query = session.createQuery(sql + sqlEnd).setFirstResult(page*pasgeSize).setMaxResults(pasgeSize);
        List<ViewSimPackage> list = query.list();
        return list;
    }

    public long totalItem(int nationId, int mobileCarrierId, int packageId, double price, String codeLanguage, String description){
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createQuery("select count(vsp) from ViewSimPackage  vsp where (-1 = '"+nationId+"' or vsp.nationId = '"+nationId+"') " +
                "and (-1 = '"+mobileCarrierId+"' or vsp.mobileCarrierId = '"+mobileCarrierId+"') and (-1 = '"+packageId+"' or vsp.packageId = '"+packageId+"') " +
                "and (-1 = '"+price+"' or vsp.price < '"+price+"') and vsp.codeLanguage = '"+codeLanguage+"' and vsp.status = 1 " +
                "and (-1 = '"+description+"' or vsp.description like '%"+description+"%')").getSingleResult();
    }
}
