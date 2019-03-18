package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.entities.Entity;

public interface IValidator<E extends Entity> {
    boolean isValidEntity(E entity);
    boolean isValidLong(String id);
}
