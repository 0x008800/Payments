package com.oxoo88oo.regularpayment.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Entity implements Serializable {
    protected long id;
}
