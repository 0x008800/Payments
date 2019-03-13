package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.DAO.DAOH2;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.DAO.IDAO;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.List;

//валидация входящих данных
public abstract class Validation {

   // @Autowired
   // @Qualifier("daoh2")
    public static IDAO dao = new DAOH2();

    //создание платежа
    public static boolean createPayment(Payment payment) throws ImpossibleException {
        if(Helper.isValidPayment(payment)){
            return dao.createPayment(payment);
        }else throw new ImpossibleException("invalid payment");

    }

    //обновление платежа
    public static boolean updatePayment(Payment payment) throws ImpossibleException {
        if(Helper.isValidPayment(payment)){
            return dao.updatePayment(payment);
        }else throw new ImpossibleException("invalid payment");
    }

    //инфо по платежу
    public static Payment getPaymentInfo(String id) throws ImpossibleException {
        if(Helper.isValidLong(id)){
            return dao.getPaymentInfo(Long.parseLong(id));
        }else throw new ImpossibleException("id is wrong");
    }

    //оплатить, сделать проводку
    public static boolean usePayment(String id) throws ImpossibleException {

        //todo
        BigDecimal count = new BigDecimal(100);
        Status status = Status.ACTIVE;

        //нужно ли проверять на необходимость проводки или это отдельн метод?
        if(Helper.isValidLong(id) && dao.isNeedToPay(Long.parseLong(id))) {
            return dao.usePayment(Long.parseLong(id), count, status);
        }else {
            //либо айди либо не нуждается в проводке
            throw new ImpossibleException("impossible");
        }
    }

    //удалить платёж
    public static boolean deletePaymentByID(String id)throws ImpossibleException{

        if(Helper.isValidLong(id)) {
            return dao.deletePayment(Long.parseLong(id));
        } else  {
            throw new ImpossibleException("wrong id");
        }
    }

    //получить все платежи по инн отправителя
    public static List<Payment> getPaymentsInfoByINN(String inn) throws ImpossibleException {
        if(Helper.isValidLong(inn)){
            return dao.getAllPaymentsByINN(Long.valueOf(inn));
        }else throw new ImpossibleException("inn is wrong");
    }

    //получить все платежи по окпо получателя
    public static List<Payment> getPaymentsInfoByOKPO(String okpo) throws ImpossibleException {
        if(Helper.isValidLong(okpo)){
            return dao.getAllPaymentsByOKPO(Long.valueOf(okpo));
        }else throw new ImpossibleException("okpo is wrong");
    }

    //получить все платежи получателя по аккаунту
    public static List<Payment> getAllReceiverPaymentsByAccaunt(String acc) throws ImpossibleException {
        if(Helper.isValidLong(acc)){
            return  dao.getAllReceiverPaymentsByAccaunt(Long.valueOf(acc));
        }else throw new ImpossibleException("acc is wrong");
    }

    //получить список проводок по платежу
    public static List<Provodka> getInfoByProvodkiOfPayment(String id) throws ImpossibleException {

        if(Helper.isValidLong(id)){
            return dao.getInfoByProvodkiOfPayment(Long.parseLong(id));
        }else throw new ImpossibleException("id is wrong");
    }


    //создать проводку
    public static boolean createProvodka(Provodka provodka) throws ImpossibleException{
        if (Helper.isValidProvodka(provodka)){
            return dao.createProvodka(provodka);
        }else throw new ImpossibleException("invalid provodka");
    }

    //обновить проводку
    public static boolean updateProvodka(Provodka provodka) throws ImpossibleException {
        if (Helper.isValidProvodka(provodka)){
            return dao.updateProvodka(provodka);
        }else throw new ImpossibleException("invalid provodka");
    }

    //удаление проводки
    public static boolean deleteProvodka(String id) throws ImpossibleException {
        if (Helper.isValidLong(id)){
            return dao.deleteProvodka(Long.parseLong(id));
        }throw new ImpossibleException("id is wrong");
    }

    //получение проводки по айди
    public static Provodka getProvodkaByID(String id) throws ImpossibleException {
        if (Helper.isValidLong(id)){
            return dao.getProvodkaByID(Long.parseLong(id));
        }else throw new ImpossibleException("id is wrong");
    }

    //сторнировка проводки
    public static boolean stornirovkaProvodki(String id) throws ImpossibleException {
        if (Helper.isValidLong(id)){
            return dao.stornirovkaProvodki(Long.parseLong(id));
        }else throw new ImpossibleException("id is wrong");
    }

    //нуждается ли платёж в проводке
    public static boolean isNeedToPay(String id) throws ImpossibleException {
        if (Helper.isValidLong(id)){
            return dao.isNeedToPay(Long.valueOf(id));
        }throw new ImpossibleException("id is wrong");
    }

    //создать все таблицы
    public static boolean createAllTables() throws ImpossibleException {
        return dao.createAllTables();
    }

    //удалить все табл
    public static boolean dropAllTables() throws ImpossibleException {
        return dao.dropAllTables();

    }
}
