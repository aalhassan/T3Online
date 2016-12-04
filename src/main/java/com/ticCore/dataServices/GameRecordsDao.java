package com.ticCore.dataServices;

import com.ticCore.beans.GameRecord;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**Database objects  for game record transactions
 * Created by student on 11/18/16.
 */
@Service
public class GameRecordsDao extends BaseDao {
    @Override
    protected Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }


    @Override
    public List<?> get(HashMap<String,Object> restrictions)
            throws HibernateException {
        Session session= getSession();
        Criteria criteria = session.createCriteria(GameRecord.class);
        criteria.add(Restrictions.allEq(restrictions));
        List<?> gameRecords = criteria.list();
        closeSession(session);
        return gameRecords;
    }

    @Override
    public List<?> getWithLimit(HashMap<String,Object> restrictions, int limit)
            throws HibernateException {
        Session session= getSession();
        Criteria criteria = session.createCriteria(GameRecord.class);
        criteria.setMaxResults(limit);
        criteria.add(Restrictions.allEq(restrictions));
        List<?> gameRecords = criteria.list();
        closeSession(session);
        return gameRecords;
    }

    @Override
    public <T> Serializable create(T entity) throws HibernateException {
        Session session= getSession();
        final Transaction transaction = session.beginTransaction();
        Serializable id = session.save(entity);
        transaction.commit();
        closeSession(session);
        return id;
    }


}
