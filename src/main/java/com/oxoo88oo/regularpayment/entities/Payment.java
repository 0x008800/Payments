package com.oxoo88oo.regularpayment.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Payment {
    private long id;
    private String nameOfSender;
    private long INNofSender;
    private long NumberOfCardOfSender;
    private long accauntOfReceiver;
    private long MFOofReceiver;
    private long OKPOofReceiver;
    private String nameOfReceiver;
    private long period;
    private BigDecimal count;
}
