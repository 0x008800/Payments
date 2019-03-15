package com.oxoo88oo.regularpayment.trash;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;

public interface IDAOParent <T extends Entity>{
    boolean create(T entity)throws ImpossibleException;
    boolean update(T entity) throws ImpossibleException;
    boolean delete(long id) throws  ImpossibleException;
}
