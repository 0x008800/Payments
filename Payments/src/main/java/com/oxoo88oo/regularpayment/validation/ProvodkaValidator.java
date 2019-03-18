package com.oxoo88oo.regularpayment.validation;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.entities.Provodka;

public class ProvodkaValidator extends ParentValidator<Provodka> {
    @Override
    public boolean checkEntity(Entity e) {
        Provodka provodka = (Provodka) e;
        return provodka != null && provodka.getId() > 0;
    }
}
