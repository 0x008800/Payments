package com.oxoo88oo.regularpayment.DAO;

import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.db.DBConnector;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//@Component("daoh2")
public class DAOH2 implements IDAO {

    //создание платежа
    @Override
    public boolean createPayment(Payment payment) throws ImpossibleException {

       return DBConnector.createPayment(payment);

    }

    //обновление платежа
    @Override
    public boolean updatePayment(Payment payment) {
        return DBConnector.updatePayment(payment);
    }

    //нужно ли сделать проводку по платежу?
    @Override
    public boolean isNeedToPay(Long id) throws ImpossibleException {
        return DBConnector.isNeededToPay(id);
    }

    //удалить платёж
    @Override
    public boolean deletePayment(long id) throws ImpossibleException {
        return DBConnector.deletePayment(id);
    }

    //инфо по платежу
    @Override
    public Payment getPaymentInfo(Long id)throws ImpossibleException {
        return DBConnector.getPaymentById(id);
    }


    //сделать проводку по платежу
    @Override
    public boolean usePayment(long id, BigDecimal count, Status status) throws ImpossibleException {

        return DBConnector.usePayment(id, count, status);

    }

    //все платежи отправителя по ИНН
    @Override
    public List<Payment> getAllPaymentsByINN(long inn)throws ImpossibleException{

        return DBConnector.getAllSendersPaymentsByINN(inn);
    }

    //все платежи получателя по окпо
    @Override
    public List<Payment> getAllPaymentsByOKPO(long okpo)throws ImpossibleException {
        return DBConnector.getAllPaymentsByOKPO(okpo);
    }

    @Override
    public List<Payment> getAllReceiverPaymentsByAccaunt(long accaunOfReceiver) throws ImpossibleException {
        return DBConnector.getAllReceiverPaymentsByAccaunt(accaunOfReceiver);
    }

    @Override
    public List<Provodka> getInfoByProvodkiOfPayment(long id) throws ImpossibleException {
        return DBConnector.getInfoByProvodkiOfPayment(id);
    }

    //создать все таблицы
    @Override
    public boolean createAllTables() throws ImpossibleException {

        return DBConnector.createAllTables();
    }

    //удалить все таблицы
    @Override
    public boolean dropAllTables() throws ImpossibleException {
        return DBConnector.dropAllTables();
    }

    @Override
    public List<Payment> getAllPayments() throws ImpossibleException {
        return DBConnector.getAllPayments();
    }

    @Override
    public List<Provodka> getAllProvodki() throws ImpossibleException {
        return DBConnector.getAllProvodki();
    }

    @Override
    public void writeAllProvodki(List<Provodka> retAllProvodokWaitingFor) throws ImpossibleException {

        DBConnector.writeAllProvodki(retAllProvodokWaitingFor);
    }

    @Override
    public boolean createProvodka(Provodka provodka) throws ImpossibleException {
        return DBConnector.createProvodka(provodka);
    }

    @Override
    public boolean updateProvodka(Provodka provodka)throws  ImpossibleException {
        return DBConnector.updateProvodka(provodka);
    }

    @Override
    public boolean deleteProvodka(Long id)throws  ImpossibleException {
        return DBConnector.deleteProvodka(id);
    }

    @Override
    public Provodka getProvodkaByID(Long id)throws  ImpossibleException {
        return DBConnector.getProvodkaByID(id);
    }

    @Override
    public boolean stornirovkaProvodki(Long id)throws  ImpossibleException {
        return DBConnector.stornirovkaProvodki(id);
    }

}
