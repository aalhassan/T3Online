package com.ticCore.dataServices;

import org.hibernate.Session;

/**
 * Created by Mej on 9/17/16.
 */
public class PlayersDao extends BaseDao {
    protected Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}
