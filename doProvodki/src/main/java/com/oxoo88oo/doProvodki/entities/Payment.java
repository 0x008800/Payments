package com.oxoo88oo.doProvodki.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
public class  Payment extends Entity {
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
