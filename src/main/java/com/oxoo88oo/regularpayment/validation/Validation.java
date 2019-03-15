package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;

//валидация входящих данных
public class Validation implements IValidation{



    //создание платежа
    public  boolean createPayment(Payment payment) throws ImpossibleException {
        if(isValidPayment(payment)){
            return true;

        }else throw new ImpossibleException("invalid payment");

    }

    //обновление платежа
    public  boolean updatePayment(Payment payment) throws ImpossibleException {
        if(isValidPayment(payment)){
            return true;

        }else throw new ImpossibleException("invalid payment");    }

    //инфо по платежу
    public boolean getPaymentInfo(String id) throws ImpossibleException {
        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("id is wrong");
    }

    //оплатить, сделать проводку
    public  boolean usePayment(String id) throws ImpossibleException {

//        //todo
//        BigDecimal count = new BigDecimal(100);
//        Status status = Status.ACTIVE;

//        if(dao.isNeedToPay(Long.parseLong(id))) {
//            return dao.usePayment(Long.parseLong(id), count, status);
//        }else {
//            return false;
//        }

        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("");
    }

    //удалить платёж
    public  boolean deletePaymentByID(String id)throws ImpossibleException{

        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("");
    }


    //получить все платежи по инн отправителя
    public boolean getPaymentsInfoByINN(String inn) throws ImpossibleException {
        if(isValidLong(inn)){
            return true;
        }else throw new ImpossibleException("");

    }

    //получить все платежи по окпо получателя
    public boolean getPaymentsInfoByOKPO(String okpo) throws ImpossibleException {
        if(isValidLong(okpo)){
            return true;
        }else throw new ImpossibleException("");

    }

//    //создать все таблицы
//    public  boolean createAllTables() throws ImpossibleException {
//        return dao.createAllTables();
//    }
//
//    //удалить все табл
//    public  boolean dropAllTables() throws ImpossibleException {
//        return dao.dropAllTables();
//
//    }

    public boolean getAllReceiverPaymentsByAccaunt(String accaunOfReceiver) throws ImpossibleException {
        if(isValidLong(accaunOfReceiver)){
            return true;
        }else throw new ImpossibleException("");
    }

    public boolean getInfoByProvodkiOfPayment(String id) throws ImpossibleException {

        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("");
    }


    public  boolean createProvodka(Provodka provodka) throws ImpossibleException{
        if(isValidProvodka(provodka)){
            return true;
        }else throw new ImpossibleException("");
    }



    public  boolean updateProvodka(Provodka provodka) throws ImpossibleException {
        if(isValidProvodka(provodka)){
            return true;
        }else throw new ImpossibleException("");
    }

    public  boolean deleteProvodka(String id) throws ImpossibleException {
        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("");
    }

    public boolean getProvodkaByID(String id) throws ImpossibleException {
        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("");
    }

    public  boolean stornirovkaProvodki(String id) throws ImpossibleException {
        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("");     }

    public  boolean isNeedToPay(String id) throws ImpossibleException {
        if(isValidLong(id)){
            return true;
        }else throw new ImpossibleException("");     }


     boolean isValidPayment(Payment payment) {
        return payment != null &&
                payment.getId() == 0 &&
                !payment.getNameOfSender().equals("") &&
                payment.getNumberOfCardOfSender() > 0 &&
                payment.getINNofSender() > 0 &&
                !payment.getNameOfReceiver().equals("") &&
                payment.getMFOofReceiver() > 0 &&
                payment.getOKPOofReceiver() > 0 &&
                payment.getPeriod() % 60_000 == 0 &&
                payment.getCount() != null;
    }

    static boolean isValidLong(String id) {
        long l;
        try {
            l = Long.parseLong(id);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return l > 0;
    }

    private boolean isValidProvodka(Provodka provodka) {
        return true;
    }
}
