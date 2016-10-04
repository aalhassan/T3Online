package com.ticCore.dataServices;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * DB Services know how to communicate with DB, fetch, create db objects uses a CrudHelper
 * for common CRUD operations
 * @author  Mej
 * @since 09/2016
 */
public abstract class BaseDao {

    public static int FETCH_ALL = 0;
    public static String ALL_COLUMNS = null;


    /**
     * Gets the hibernate session for perfroming DB transactions
     * @return Session to perform db transactions  */


    abstract protected Session getSession() ;


    /**
     * Gets  record(s)  from column using criteria
     * @param criteria  A list of column/value restriction mappings
     * @param limit  max records to fetch (0 means fetch all)
     * @return Object(s) gotten from DB or null
     */

    public  List<?>  getWithLimit(HashMap<String,Object> criteria , int limit)
            throws HibernateException   {
        return null;
    }

    /**
     * Gets  record(s)  from column using criteria
     * @param criterions  A list of column/value restriction mappings
     * @return Object(s) gotten from DB or null
     */

    public List<?>  get(HashMap<String,Object> criterions ) throws HibernateException {
        return new ArrayList<Object>();
    }

    /**
     * Creates a new record in DB
     * @param entity entity whose record will be created
     * @return whether creation was successful or not     */


    public <T> Serializable create(T entity) throws HibernateException {

        return getSession().save(entity);
    }

    /**
     * View all records
     * @return Object(s) gotten from DB or null
     */

    public List<?> getAll() throws HibernateException {
        return null;
    }

    /**
     * Updates an existing record
     * @param entity entity whose record will be updated
     */

    public <T> void  update(T entity) throws HibernateException {

        this.getSession().update(entity);
    }

    /**
     * Deletes an existing record in DB
     * @param entity entity whose record will be updated
     */

    public <T> void  delete(T entity) throws HibernateException {
        getSession().delete(entity);
    }

}
