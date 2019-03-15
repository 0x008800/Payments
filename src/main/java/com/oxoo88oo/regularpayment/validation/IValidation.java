package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;

public interface IValidation {
      boolean createPayment(Payment payment) throws ImpossibleException ;

    //обновление платежа
      boolean updatePayment(Payment payment) throws ImpossibleException ;

    //инфо по платежу
      boolean getPaymentInfo(String id) throws ImpossibleException ;

    //оплатить, сделать проводку
      boolean usePayment(String id) throws ImpossibleException;

    //удалить платёж
      boolean deletePaymentByID(String id)throws ImpossibleException;

    //получить все платежи по инн отправителя
      boolean getPaymentsInfoByINN(String inn) throws ImpossibleException;

    //получить все платежи по окпо получателя
      boolean getPaymentsInfoByOKPO(String okpo) throws ImpossibleException;
    //создать все таблицы
//      boolean createAllTables() throws ImpossibleException ;
//
//    //удалить все табл
//      boolean dropAllTables() throws ImpossibleException ;

      boolean getAllReceiverPaymentsByAccaunt(String accaunOfReceiver) throws ImpossibleException;

      boolean getInfoByProvodkiOfPayment(String id) throws ImpossibleException;


      boolean createProvodka(Provodka provodka) throws ImpossibleException;

      boolean updateProvodka(Provodka provodka) throws ImpossibleException;

      boolean deleteProvodka(String id) throws ImpossibleException;

      boolean getProvodkaByID(String id) throws ImpossibleException;

      boolean stornirovkaProvodki(String id) throws ImpossibleException ;

      boolean isNeedToPay(String id) throws ImpossibleException ;
}
