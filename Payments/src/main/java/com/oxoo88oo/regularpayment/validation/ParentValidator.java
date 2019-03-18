package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.entities.Entity;

public abstract class ParentValidator<E extends Entity> implements IValidator<E>{

    @Override
    public boolean isValidEntity(Entity entity) {
        return checkEntity(entity);

    }

    @Override
    public boolean isValidLong(String id) {
        long l;
        try {
            l = Long.parseLong(id);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return l > 0;
    }

    public abstract boolean checkEntity(Entity e);
}
