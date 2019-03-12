package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.entities.Payment;

import java.math.BigDecimal;

class Utils {

    static boolean isValidPayment(Payment payment) {
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
}
