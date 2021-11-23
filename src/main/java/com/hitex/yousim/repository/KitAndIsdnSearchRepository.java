package com.hitex.yousim.repository;

import com.hitex.yousim.model.view.KitAndIsdn;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KitAndIsdnSearchRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public KitAndIsdnSearchRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public List<KitAndIsdn> searchKitAndIsdn(int nationId, int mobileCarrierId, int stockModelId, double price,
                                      String firstIsdn, String isdn, int page, int pageSize, String sqlEnd){
        String sql = "select k from KitAndIsdn  k where (-1 = '"+nationId+"' or k.nationId = '"+nationId+"') " +
                "and (-1 = '"+mobileCarrierId+"' or k.mobileCarrierId = '"+mobileCarrierId+"') " +
                "and (-1 = '"+stockModelId+"' or k.stockModelId = '"+stockModelId+"') and (-1 = '"+price+"' or k.priceAmount < '"+price+"') " +
                "and k.status = 1 and (-1 = '"+firstIsdn+"' or k.isdn like '"+firstIsdn+"%') and (-1 = '"+isdn+"' or k.isdn like '%"+firstIsdn+"%')";
        Session session = sessionFactory.getCurrentSession();
        Query<KitAndIsdn> query = session.createQuery(sql + sqlEnd).setFirstResult(page*pageSize).setMaxResults(pageSize);
        List<KitAndIsdn> list = query.list();
        return list;
    }

    public long totalItem(int nationId, int mobileCarrierId, int stockModelId, double price, String firstIsdn, String isdn){
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createQuery("select count(k) from KitAndIsdn  k where (-1 = '"+nationId+"' or k.nationId = '"+nationId+"') " +
                "and (-1 = '"+mobileCarrierId+"' or k.mobileCarrierId = '"+mobileCarrierId+"') and (-1 = '"+stockModelId+"' or k.stockModelId = '"+stockModelId+"') " +
                "and (-1 = '"+price+"' or k.priceAmount < '"+price+"') and k.status = 1 " +
                "and (-1 = '"+firstIsdn+"' or k.isdn like '"+firstIsdn+"%') and (-1 = '"+isdn+"' or k.isdn like '%"+firstIsdn+"%') ").getSingleResult();
    }
}
