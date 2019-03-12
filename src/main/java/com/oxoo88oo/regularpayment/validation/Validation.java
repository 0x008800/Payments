package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.DAO.DAOH2;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.DAO.DAO;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.List;

//валидация входящих данных
public abstract class Validation {

    @Autowired
    @Qualifier("daoh2")
    public static DAO dao = new DAOH2();

    //создание платежа
    public static boolean createPayment(Payment payment) throws ImpossibleException {
        if(Utils.isValidPayment(payment)){
            return dao.createPayment(payment);
        }else throw new ImpossibleException("invalid payment");

    }

    //обновление платежа
    public static boolean updatePayment(Payment payment) throws ImpossibleException {
        return dao.updatePayment(payment);
    }

    //инфо по платежу
    public static Payment getPaymentInfo(String id) throws ImpossibleException {
        if(Utils.isValidLong(id)){
            return dao.getPaymentInfo(Long.parseLong(id));
        }else throw new ImpossibleException("id is wrong");
    }

    //оплатить, сделать проводку
    public static boolean usePayment(String id) throws ImpossibleException {

        //todo
        BigDecimal count = new BigDecimal(100);
        Status status = Status.ACTIVE;

        if(dao.isNeedToPay(Long.parseLong(id))) {
            return dao.usePayment(Long.parseLong(id), count, status);
        }else {
            return false;
        }
    }

    //удалить платёж
    public static boolean deletePaymentByID(String id)throws ImpossibleException{

        if(Utils.isValidLong(id)) {
            return dao.deletePayment(Long.parseLong(id));
        } else  {
            throw new ImpossibleException("wrong id");
        }
    }

    //получить все платежи по инн отправителя
    public static List<Payment> getPaymentsInfoByINN(String inn) throws ImpossibleException {
        return dao.getAllPaymentsByINN(Long.valueOf(inn));
    }

    //получить все платежи по окпо получателя
    public static List<Payment> getPaymentsInfoByOKPO(String okpo) throws ImpossibleException {
        return dao.getAllPaymentsByOKPO(Long.valueOf(okpo));
    }

    //создать все таблицы
    public static boolean createAllTables() throws ImpossibleException {
        return dao.createAllTables();
    }

    //удалить все табл
    public static boolean dropAllTables() throws ImpossibleException {
        return dao.dropAllTables();

    }

    public static List<Payment> getAllReceiverPaymentsByAccaunt(String accaunOfReceiver) throws ImpossibleException {
        return  dao.getAllReceiverPaymentsByAccaunt(Long.valueOf(accaunOfReceiver));
    }

    public static List<Provodka> getInfoByProvodkiOfPayment(String id) throws ImpossibleException {

        return dao.getInfoByProvodkiOfPayment(Long.valueOf(id));
    }


    public static boolean createProvodka(Provodka provodka) throws ImpossibleException{
        return dao.createProvodka(provodka);
    }

    public static boolean updateProvodka(Provodka provodka) throws ImpossibleException {
        return dao.updateProvodka(provodka);
    }

    public static boolean deleteProvodka(String id) throws ImpossibleException {
        return dao.deleteProvodka(Long.valueOf(id));
    }

    public static Provodka getProvodkaByID(String id) throws ImpossibleException {
        return dao.getProvodkaByID(Long.valueOf(id));
    }

    public static boolean stornirovkaProvodki(String id) throws ImpossibleException {
        return dao.stornirovkaProvodki(Long.valueOf(id));
    }

    public static boolean isNeedToPay(String id) throws ImpossibleException {
        return dao.isNeedToPay(Long.valueOf(id));
    }
}
