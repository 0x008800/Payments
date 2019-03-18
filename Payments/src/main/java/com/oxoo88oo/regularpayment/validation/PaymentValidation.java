package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;

//валидация входящих данных
public class PaymentValidation extends ParentValidator<Payment> {

    @Override
    public boolean checkEntity(Entity e) {
        Payment payment = (Payment) e;
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
}
