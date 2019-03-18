package com.oxoo88oo.doProvodki.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
public class Provodka extends Entity {
    private long idOfPayment;
    private long time;
    private BigDecimal count;
    private Status status;
}
