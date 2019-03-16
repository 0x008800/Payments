package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;

//валидация входящих данных
public class Validation{

     public boolean isValidPayment(Entity entity) {
        Payment payment = (Payment) entity;
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

     public boolean isValidLong(String id) {
        long l;
        try {
            l = Long.parseLong(id);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return l > 0;
    }

    public boolean isValidProvodka(Provodka provodka) {
        return true;
    }
}
