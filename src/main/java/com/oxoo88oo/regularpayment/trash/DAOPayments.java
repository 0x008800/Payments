package com.oxoo88oo.regularpayment.trash;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.trash.IDAOPayment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOPayments<Payment> implements IDAOPayment {

    private final static String insertIntoPayments =
            "INSERT INTO PAYMENTS (nameOfSender, inn_of_sender , namber_of_card_of_sender," +
                    " accaun_of_receiver, mfo_of_receiver, okpo_of_receiver, name_of_receiver," +
                    " period, count) values (?,?,?,?,?,?,?,?,?)";
    @Override
    public Payment getPaymentInfo(Long id) throws ImpossibleException {
        return null;
    }

    @Override
    public boolean usePayment(long id, BigDecimal count, Status status) throws ImpossibleException {
        return false;
    }

    @Override
    public List getAllPaymentsByINN(long id) throws ImpossibleException {
        return null;
    }

    @Override
    public List getAllPaymentsByOKPO(long okpo) throws ImpossibleException {
        return null;
    }

    @Override
    public List getAllReceiverPaymentsByAccaunt(long accaunOfReceiver) throws ImpossibleException {
        return null;
    }

    @Override
    public List<Provodka> getInfoByProvodkiOfPayment(long id) throws ImpossibleException {
        return null;
    }

    @Override
    public List getAllPayments() throws ImpossibleException {
        return null;
    }

    @Override
    public List<Provodka> getAllProvodki() throws ImpossibleException {
        return null;
    }

    @Override
    public boolean isNeedToPay(Long id) throws ImpossibleException {
        return false;
    }

    @Override
    public boolean createAllTables() throws ImpossibleException {
        return false;
    }

    @Override
    public boolean dropAllTables() throws ImpossibleException {
        return false;
    }

    @Override
    public Provodka getProvodkaByID(Long valueOf) throws ImpossibleException {
        return null;
    }

    @Override
    public boolean stornirovkaProvodki(Long id) throws ImpossibleException {
        return false;
    }

    @Override
    public void writeAllProvodki(List retAllProvodokWaitingFor) throws ImpossibleException {

    }

    @Override
    public boolean create(Payment payment) throws ImpossibleException {
        try (Connection conn =  DriverManager.getConnection("jdbc:h2:~/db/payments", "sa", "");
             PreparedStatement ps = conn.prepareStatement(insertIntoPayments)){

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

        }catch (SQLException e){
            e.printStackTrace();
            throw new ImpossibleException("");
        }
        return true;
    }

    @Override
    public boolean create(Entity entity) throws ImpossibleException {
        return false;
    }

    @Override
    public boolean update(Entity entity) throws ImpossibleException {
        return false;
    }

    @Override
    public boolean delete(long id) throws ImpossibleException {
        return false;
    }
}
