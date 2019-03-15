package com.oxoo88oo.regularpayment.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    boolean insert(T entity)throws SQLException;
    boolean update(T entity) throws SQLException;

    boolean delete(long id) throws  SQLException;

    Connection getConnection() throws SQLException;
    String getTable();

   T getByID(long id) throws SQLException;

   List<T> getAll()throws SQLException;
}
