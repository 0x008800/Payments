package com.oxoo88oo.regularpayment.trash;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;

public interface CRUD <T>{
    boolean create(T t)throws ImpossibleException;
    boolean update(T t) throws ImpossibleException;
    boolean delete(long id) throws  ImpossibleException;
}
