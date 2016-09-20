package com.ticCore.dataServices;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * DB Services know how to communicate with DB, fetch, create db objects
 * @author  Mej
 * @since 09/2016
 */
public abstract class AbstractDBService {

    public static int FETCH_ALL = 0;
    public static String ALL_COLUMNS = null;
    /**
     * Gets the hibernate session for perfroming DB transactions
     * @return Session to perform db transactions  */


    protected Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * creates a new record in DB
     * @param entity entity whose record will be created
     * @return whether creation was successful or not     */


    abstract public <T> void  create(T entity) ;

    /**
     * Gets all records by  from DB
     * @return Object(s) gotten from DB or null
     */

    abstract public <T> List<T> getAll() ;

    /**
     * Updates an existing record in DB
     * @param entity entity whose record will be updated
     */

    abstract public <T> void  update(T entity) ;

    /**
     * Deletes an existing record in DB
     * @param entity entity whose record will be updated
     */

    abstract public <T> void  delete(T entity);

    /**
     * Gets  record(s)  from column using criteria
     * @param criteria  A list of column/value restriction mappings
     * @param limit  max records to fetch (0 means fetch all)
     * @return Object(s) gotten from DB or null
     */

    public  List<?>  getWithLimit(List<HashMap<String,Object>> criteria , int limit) {
        return null;
    }

    /**
     * Gets  record(s)  from column using criteria
     * @param criteria  A list of column/value restriction mappings
     * @return Object(s) gotten from DB or null
     */

    public List<?>  get(List<Map<String,?>> criteria) {
        return null;

    }

}
