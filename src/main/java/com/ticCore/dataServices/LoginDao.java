package com.ticCore.dataServices;

import com.ticCore.beans.Login;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student on 9/19/16.
 */
public class LoginDao  extends  AbstractDBService {


    @Override
    public List<?> getWithLimit(List<HashMap<String,Object>> restrictions, int limit) {
        Criteria criteria = getSession().createCriteria(Login.class);
        criteria.setMaxResults(limit);
        for (HashMap<String, Object> criterion : restrictions) {
            criteria.add(Restrictions.allEq(criterion));
        }
        return criteria.list();
    }

    @Override
    public <T> List<T> getAll() {
       return null;
    }

    @Override
    public <T> void create(T entity) {
        //
    }

    @Override
    public <T> void update(T entity) {
        //
    }

    @Override
    public <T> void delete(T entity) {
        //
    }
}
