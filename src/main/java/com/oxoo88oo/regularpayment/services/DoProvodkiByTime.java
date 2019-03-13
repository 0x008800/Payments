package com.oxoo88oo.regularpayment.services;

import com.oxoo88oo.regularpayment.DAO.DAOH2;
import com.oxoo88oo.regularpayment.DAO.IDAO;
import com.oxoo88oo.regularpayment.entities.Status;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoProvodkiByTime {

   // @Autowired
   // @Qualifier("daoh2")
    IDAO dao = new DAOH2();
    //= new DAOH2();

    Logger logger = LoggerFactory.getLogger(DoProvodkiByTime.class);

    public void doProvodki(String[] args) {
        System.out.println("do provodki");
        logger.info("start service");
        try {

            long start = 0;
            long end = 0;
            long operationsTime = 0;

            while (true){
                start = System.currentTimeMillis();

                logger.debug("get all payments");
                List<Payment> allPayments = dao.getAllPayments();

                logger.debug("get all provodki");
                List<Provodka> allProvodki = dao.getAllProvodki();

                logger.debug("write all provodki");
                dao.writeAllProvodki(retAllProvodokWaitingFor(allPayments, allProvodki));

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
        catch (ImpossibleException e){
            e.getMessage();
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

