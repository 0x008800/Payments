package com.oxoo88oo.regularpayment.DAO;

import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOProvodki extends DAOParent<Provodka> {




    protected DAOProvodki() throws SQLException {
        this.table = "PROVODKI";
    }

    @Override
    public  boolean insert(Provodka provodka) throws SQLException{

        try (PreparedStatement ps = connection.prepareStatement(WorkWithTables.insertIntoProvodki)) {
            setProvodka(provodka, ps);
        }
        return true;
    }

    @Override
    public  boolean update(Provodka provodka)throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(WorkWithTables.updateProvodkaQuery)) {
            updateProvodka(provodka, ps);
        }
        return true;
    }



    public long stornirovka(long id)throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement(updateStatusProvodkaQuery)) {
            ps.setString(1, Status.STORNIRE.toString());
            ps.setLong(2, id);

            return ps.executeUpdate();
        }
    }

    public boolean writeAllProvodki(List<Provodka> retAllProvodokWaitingFor) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(insertIntoProvodki)) {

            for (Provodka provodka : retAllProvodokWaitingFor) {

                setProvodka(provodka, ps);
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        }

    }



    public List<Provodka> parseResultSet(ResultSet rs)throws SQLException {

        Provodka provodka;
        List<Provodka> provodkas = new LinkedList<>();
            while (rs.next()){
                provodka  = new Provodka();

                provodka.setId(rs.getInt(1));
                provodka.setIdOfPayment(rs.getInt(2));
                provodka.setTime(rs.getLong(3));
                provodka.setCount(rs.getBigDecimal(4));
                provodka.setStatus(Status.valueOf(rs.getString(5)));

            }
            return provodkas;
    }

    public void setProvodka(Provodka provodka, PreparedStatement ps) throws SQLException {
        updateProvodka(provodka, ps);
        ps.setLong(5, provodka.getId());

        ps.executeUpdate();
    }

    private void updateProvodka(Provodka provodka, PreparedStatement ps) throws SQLException{
        ps.setLong(1, provodka.getIdOfPayment());
        ps.setLong(2, System.currentTimeMillis());
        ps.setBigDecimal(3, provodka.getCount());
        ps.setString(4, provodka.getStatus().toString());
    }
}
