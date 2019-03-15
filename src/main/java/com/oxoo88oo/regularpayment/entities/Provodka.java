package com.oxoo88oo.regularpayment.entities;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Provodka extends Entity {
    private long idOfPayment;
    private long time;
    private BigDecimal count;
    private Status status;
}
