package com.ticCore.dataServices;

import java.util.List;

/**
 * Created by student on 9/19/16.
 */
public abstract class LoginDao  extends  AbstractDBService {
    @Override
    public <T> void create(T entity) {

    }

    @Override
    public <T> List<T> getAll() {
        return null;
    }

    @Override
    public <T> List<?> getWithLimit(String column, T match, int limit) {
        return null;
    }

    @Override
    public <T> List<?> get(String column, T match) {
        return null;
    }

    @Override
    public <T> void update(T entity) {

    }

    @Override
    public <T> void delete(T entity) {

    }
}
