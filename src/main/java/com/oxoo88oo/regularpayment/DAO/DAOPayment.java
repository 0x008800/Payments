package com.oxoo88oo.regularpayment.DAO;

import com.oxoo88oo.regularpayment.entities.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOPayment extends DAOParent<Payment> {

    public DAOPayment() throws SQLException {
        this.table = "PAYMENTS";
    }

    @Override
    public boolean insert(Payment payment) throws SQLException {
        try (PreparedStatement ps =
        connection.prepareStatement(WorkWithTables.createPaymentTableQuery)) {
            setPayment(payment, ps);
        }
        return true;
    }


    @Override
    public boolean update(Payment payment) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(WorkWithTables.updatePaymentQuery)){

            setPayment(payment, ps);
        }
            return true;
    }

    @Override
    public List<Payment> getByColumn(long id, long column) throws SQLException {
        List<Payment> list;
        try(PreparedStatement ps = connection.prepareStatement(String.format(getWhereQuery, getTable(), column))){

            ResultSet rs = ps.executeQuery();
            list = parseResultSet(rs);
        }
        if(list == null || list.size() == 0)return null;

        return list;
    }

    @Override
    public List<Payment> parseResultSet(ResultSet rs)throws SQLException {
        Payment payment;
        List<Payment> payments = new LinkedList<>();

        while (rs.next()){
            payment = new Payment();

            payment.setId(rs.getLong("id"));
            payment.setNameOfSender(rs.getString(2));
            payment.setINNofSender(rs.getLong(3));
            payment.setNumberOfCardOfSender(rs.getLong(4));
            payment.setAccauntOfReceiver(rs.getLong(5));
            payment.setMFOofReceiver(rs.getLong(6));
            payment.setOKPOofReceiver(rs.getLong(7));
            payment.setNameOfReceiver(rs.getString(8));
            payment.setPeriod(rs.getLong(9));
            payment.setCount(rs.getBigDecimal(10));

            payments.add(payment);
        }

        return payments;
    }

    public void setPayment(Payment payment, PreparedStatement ps) throws SQLException {
        ps.setString(1, payment.getNameOfSender());
        ps.setLong(2, payment.getINNofSender());
        ps.setLong(3, payment.getNumberOfCardOfSender());
        ps.setLong(4, payment.getAccauntOfReceiver());
        ps.setLong(5, payment.getMFOofReceiver());
        ps.setLong(6, payment.getOKPOofReceiver());
        ps.setString(7, payment.getNameOfReceiver());
        ps.setLong(8, payment.getPeriod());
        ps.setString(9, payment.getCount().toString());

        ps.executeUpdate();
    }



}
