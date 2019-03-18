package com.oxoo88oo.doProvodki.service;

import com.oxoo88oo.doProvodki.Main;
import com.oxoo88oo.doProvodki.client.IClient;
import com.oxoo88oo.doProvodki.client.PaymentClient;
import com.oxoo88oo.doProvodki.client.ProvodkaClient;
import com.oxoo88oo.doProvodki.entities.Payment;
import com.oxoo88oo.doProvodki.entities.Provodka;
import com.oxoo88oo.doProvodki.entities.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DoProvodki {
    static Logger logger = LoggerFactory.getLogger(Main.class);

    public void doProvodki() {
        System.out.println("do provodki");
        logger.info("start service");
        IClient<Payment> paymentClient = new PaymentClient(Payment.class);
        IClient<Provodka> provodkaClient = new ProvodkaClient(Provodka.class);
        try {

            long start = 0;
            long end = 0;
            long operationsTime = 0;

            while (true){
                start = System.currentTimeMillis();

                logger.debug("get all payments");
                List<Payment> allPayments =paymentClient.getAll();

                logger.debug("get all provodki");
                List<Provodka> allProvodki = provodkaClient.getAll();

                List<Provodka> all = retAllProvodokWaitingFor(allPayments, allProvodki);
                for (Provodka p: all){
                logger.debug("write provodka");
                provodkaClient.send(p);
                }
                logger.debug("sleep 60 sec");

                end = System.currentTimeMillis();
                operationsTime = end - start;

                //выравниваем цикл по времени
                //чтобы каждая иттерация была равна 60 сек
                Thread.sleep(60_000 - operationsTime);
                //todo операция не должна быть больше 60 сек
                //что если цикл занимает по времени в несколько
                //раз больше какихто периодов...
            }
    } catch (InterruptedException e) {
      e.printStackTrace();
        }
    }

    public List<Provodka> retAllProvodokWaitingFor(List<Payment> allPayments, List<Provodka> allProvodki) {
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
