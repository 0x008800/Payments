package com.oxoo88oo.regularpayment.DAO;

import com.oxoo88oo.regularpayment.entities.Entity;

import java.sql.*;
import java.util.List;

public abstract class DAOParent<E extends Entity> extends WorkWithTables implements IDAO<E>{

    public abstract List<E> parseResultSet(ResultSet rs) throws SQLException;

    @Override
    public String getTable() {
        return table;
    }

    String table;
    Connection connection = getConnection();
    public PreparedStatement ps = null;

    protected DAOParent() throws SQLException {
    }

    @Override
    public boolean delete(long id) throws SQLException {
    try (PreparedStatement ps = connection.prepareStatement(String.format(deleteQuery, getTable()))) {

        ps.setLong(1, id);
        ps.executeUpdate();
        }
        return true;
    }

    @Override
    public Connection getConnection()throws SQLException{
            return DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
    }

    @Override
    public E getByID(long id)throws SQLException{
        List<E> list;
        try (PreparedStatement ps = connection.prepareStatement(String.format(getByIdQuery, getTable(), id))) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            list = parseResultSet(rs);
        }
        if(list == null || list.size() == 0)return null;
        if (list.size() > 1) throw new SQLException();
        return  list.iterator().next();
    }

    @Override
    public List<E> getAll()throws SQLException{
        List<E> list;
        try(PreparedStatement ps = connection.prepareStatement(String.format(getAllQuery, getTable()))){
            ResultSet rs = ps.executeQuery();

            list = parseResultSet(rs);
        }
        if(list == null || list.size() == 0)return null;

        return list;
    }


}
