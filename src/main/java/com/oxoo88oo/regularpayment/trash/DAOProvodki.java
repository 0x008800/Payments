package com.oxoo88oo.regularpayment.trash;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.trash.IDAOProvodki;

import java.math.BigDecimal;
import java.util.List;

public class DAOProvodki implements IDAOProvodki {
    @Override
    public Payment getPaymentInfo(Long id) throws ImpossibleException {
        return null;
    }

    @Override
    public boolean usePayment(long id, BigDecimal count, Status status) throws ImpossibleException {
        return false;
    }

    @Override
    public List<Payment> getAllPaymentsByINN(long id) throws ImpossibleException {
        return null;
    }

    @Override
    public List<Payment> getAllPaymentsByOKPO(long okpo) throws ImpossibleException {
        return null;
    }

    @Override
    public List<Payment> getAllReceiverPaymentsByAccaunt(long accaunOfReceiver) throws ImpossibleException {
        return null;
    }

    @Override
    public List<Provodka> getInfoByProvodkiOfPayment(long id) throws ImpossibleException {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() throws ImpossibleException {
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
