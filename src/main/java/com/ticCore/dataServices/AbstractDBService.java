package com.ticCore.dataServices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;



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
     * Gets  record(s)  from column using criteria
     * @param column  Criteria's column (Null means no criteria match)
     * @param match  Criteria value
     * @param limit  max records to fetch (0 means fetch all)
     * @return Object(s) gotten from DB or null
     */

    abstract public  <T> List<?>  getWithLimit(String column, T match, int limit) ;

    /**
     * Gets  record(s)  from column using criteria
     * @param column  Criteria's column (Null means no criteria match)
     * @param match  Criteria value
     * @return Object(s) gotten from DB or null
     */

    abstract public <T> List<?>  get(String column, T match) ;

    /**
     * Updates an existing record in DB
     * @param entity entity whose record will be updated
     * @return whether update was successful or not
     */

    abstract public <T> void  update(T entity) ;

    /**
     * Deletes an existing record in DB
     * @param entity entity whose record will be updated
     * @return whether update was successful or not
     */

    abstract public <T> void  delete(T entity);
}
