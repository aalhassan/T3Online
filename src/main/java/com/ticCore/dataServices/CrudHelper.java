package com.ticCore.dataServices;

import java.util.List;

/**
 * Crud Helpers know how to perfor simple crud operations
 * @author  Mej
 * @since 09/2016
 */
public interface CrudHelper {
    /**
     * Creates a new record in DB
     * @param entity entity whose record will be created
     * @return whether creation was successful or not     */


    public <T> void  create(T entity) ;

    /**
     * Gets all records by  from DB
     * @return Object(s) gotten from DB or null
     */

    public List<?> getAll() ;

    /**
     * Updates an existing record in DB
     * @param entity entity whose record will be updated
     */

    public <T> void  update(T entity) ;

    /**
     * Deletes an existing record in DB
     * @param entity entity whose record will be updated
     */

    public <T> void  delete(T entity) ;


}
