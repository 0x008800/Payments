package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.DAO.IDAO;
import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.validation.Validation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

public abstract class ParentRESTController<E extends Entity> implements IController<E>{


    public IDAO idao = getDAO();
    public Validation validation = new Validation();

    protected ParentRESTController() throws SQLException {
    }

    @Override
    @PostMapping("/insert")
    public boolean insert(@RequestBody E entity)throws SQLException {
        return true;
    }

    @Override
    @GetMapping("/info/{id}")
    public Entity info(@PathVariable("id") String id)throws SQLException{
        return idao.getByID(Long.parseLong(id));
    }

    public abstract IDAO getDAO()throws SQLException;
}
