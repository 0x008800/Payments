package com.oxoo88oo.regularpayment.services;

import com.oxoo88oo.regularpayment.DAO.DAO;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.entities.Provodka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoProvodkiByTime {

    @Autowired
    @Qualifier("daoh2")
    DAO dao;
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
                dao.writeAllProvodki(Utils.retAllProvodokWaitingFor(allPayments, allProvodki));

                logger.debug("sleep 60 sec");

                end = System.currentTimeMillis();
                operationsTime = end - start;

                //выравниваем цикл по времени
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
}
