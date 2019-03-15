package com.oxoo88oo.regularpayment.trash;

import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;

import java.math.BigDecimal;
import java.util.List;

public interface IDAOProvodki<Provodki> extends IDAOParent {

    Payment getPaymentInfo(Long id)throws  ImpossibleException;

    boolean usePayment(long id, BigDecimal count, Status status) throws ImpossibleException;

    List<Payment> getAllPaymentsByINN(long id)throws  ImpossibleException;

    List<Payment> getAllPaymentsByOKPO(long okpo)throws  ImpossibleException;



    List<Payment> getAllReceiverPaymentsByAccaunt(long accaunOfReceiver) throws  ImpossibleException;

    List<Provodka> getInfoByProvodkiOfPayment(long id) throws  ImpossibleException;

    List<Payment> getAllPayments()throws  ImpossibleException;

    List<Provodka> getAllProvodki()throws  ImpossibleException;

    void writeAllProvodki(List<Provodka> retAllProvodokWaitingFor)throws  ImpossibleException;


    boolean isNeedToPay(Long id) throws ImpossibleException;

    boolean createAllTables() throws ImpossibleException;

    boolean dropAllTables()throws  ImpossibleException;


    Provodka getProvodkaByID(Long valueOf)throws  ImpossibleException;

    boolean stornirovkaProvodki(Long id)throws  ImpossibleException;
}
