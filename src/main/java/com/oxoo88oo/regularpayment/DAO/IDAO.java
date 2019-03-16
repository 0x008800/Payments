package com.oxoo88oo.regularpayment.DAO;

import com.oxoo88oo.regularpayment.entities.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<E extends Entity> {

    boolean insert(E entity)throws SQLException;
    boolean update(E entity) throws SQLException;

    boolean delete(long id) throws  SQLException;

    Connection getConnection() throws SQLException;
    String getTable();

   E getByID(long id) throws SQLException;

   List<E> getByColumn(long id, long column)throws SQLException;

   List<E> getAll()throws SQLException;
}
