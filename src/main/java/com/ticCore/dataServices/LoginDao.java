package com.ticCore.dataServices;

import com.ticCore.beans.Login;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.List;

/**
 * Created by student on 9/19/16.
 */
public class LoginDao extends BaseDao {

    @Override
    public List<?> getWithLimit(HashMap<String,Object> restrictions, int limit)
                throws HibernateException {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Login.class);
        criteria.setMaxResults(limit);
        criteria.add(Restrictions.allEq(restrictions));
        List<?> loginInfo =  criteria.list();
        closeSession(session);
        return loginInfo;
    }

}
