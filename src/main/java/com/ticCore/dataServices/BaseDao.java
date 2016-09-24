package com.ticCore.dataServices;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    private CrudHelper crudHelper;

    public CrudHelper getCrudHelper() {
        return crudHelper;
    }

    public void setCrudHelper(CrudHelper crudHelper) {
        this.crudHelper = crudHelper;
    }

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

    public  List<?>  getWithLimit(List<HashMap<String,Object>> criteria , int limit) {
        return null;
    }

    /**
     * Gets  record(s)  from column using criteria
     * @param criteria  A list of column/value restriction mappings
     * @return Object(s) gotten from DB or null
     */

    public List<?>  get(List<HashMap<String,Object>> criteria ) {
        return null;

    }

}
