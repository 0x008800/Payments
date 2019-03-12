package com.oxoo88oo;

import com.oxoo88oo.regularpayment.services.DoProvodkiByTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException {



        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        DoProvodkiByTime doProvodkiByTime = applicationContext.getBean(DoProvodkiByTime.class);
        doProvodkiByTime.doProvodki(args);
    }
}

