package com.oxoo88oo.regularpayment.services;

import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.entities.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


//todo
public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static List<Provodka> retAllProvodokWaitingFor(List<Payment> allPayments, List<Provodka> allProvodki) {
        //будем отталкиваться от этого времени
        long currentTime = System.currentTimeMillis();

        //сюда будем писать проводки которые проведутся(запишуться в таблицу проводок)
        List<Provodka> provodkasToWrite = new ArrayList<>();

        //проходимся по всем платежам
        //находим последнюю проводку к каждому платежу, если есть
        for(Payment payment : allPayments){
            Provodka tmp = null;
            long max = 0L;
            for(Provodka provodka: allProvodki){

                //если айди платежа не совпадает с айди платежа проводки,
                //то переходи к следующей
                if(payment.getId() != provodka.getIdOfPayment()) continue;

                //если время проводки больше чем в предыдущей в списке
                //по этому платежу, записываем во временную переменную её
                if(provodka.getTime() > max){
                    max = provodka.getTime();
                    tmp = provodka;
                }

            }
            //если не найдено ни одной проводки по платежу
            //значит он нуждается в первой
            if(tmp == null){
                Provodka provodka = new Provodka();
                provodka.setIdOfPayment(payment.getId());
                provodka.setTime(currentTime);
                provodka.setCount(payment.getCount());
                provodka.setStatus(Status.ACTIVE);// ???
                provodkasToWrite.add(provodka);
                logger.debug("первая проводка: " + provodka);
                continue;
            }
            //если настоящее время минус время последней проводки больше чем период платежа
            // то добавляем проводку в список проводок которые необходимо сделать
            if(currentTime - tmp.getTime() > payment.getPeriod()){
                provodkasToWrite.add(tmp);
                logger.debug("новая проводка: " + tmp);
            }
        }
        return provodkasToWrite;
    }
}
