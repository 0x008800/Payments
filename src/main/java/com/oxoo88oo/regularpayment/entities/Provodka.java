package com.oxoo88oo.regularpayment.entities;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Provodka {
    private long id;
    private long idOfPayment;
    private long time;
    private BigDecimal count;
    private Status status;
}
