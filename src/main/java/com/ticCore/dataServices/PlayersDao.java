package com.ticCore.dataServices;

import com.ticCore.beans.Player;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mej on 9/17/16.
 */
@Service
public class PlayersDao extends BaseDao {

    @Override
    public List<?> get(HashMap<String, Object> restrictions) throws HibernateException {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Player.class);
            criteria.add(Restrictions.allEq(restrictions));
        List<?> players = criteria.list();
        closeSession(session);
        return players;
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

    @Override
    public List<?> getAll() throws HibernateException {
        Session session = getSession();

        List<?> players = session.createCriteria(Player.class).list();
        closeSession(session);
        return players;

    }

    /**This particular implementation modifies a players password only
     * @param entity entity whose record will be updated
     * @throws HibernateException
     */
    @Override
    public <T> void update(T entity) throws HibernateException {
        Player player =  (Player) entity;
        Session session= getSession();

        final Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Player.class);
        criteria.add(Restrictions.eq("email", player.getEmail()));
        Player existingPlayer =  (Player) criteria.list().get(0);

        //Update all fields as necessary
        existingPlayer.setPassword(player.getPassword());
        //End of updates

        session.update(existingPlayer);
        transaction.commit();
        closeSession(session);
    }
}
