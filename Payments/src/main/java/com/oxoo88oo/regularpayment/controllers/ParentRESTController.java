package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.DAO.IDAO;
import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.validation.IValidator;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

public abstract class ParentRESTController<E extends Entity> implements IController<E>{


    public IDAO idao = getDAO();
    public IValidator validation = getValidator();

    protected ParentRESTController() throws SQLException {
    }

    @Override
    @PostMapping("/insert")
    public boolean insert(@RequestBody E entity)throws SQLException {
        if (validation.isValidEntity(entity)) return idao.insert(entity);
        else return false;
    }

    @Override
    @GetMapping("/info/{id}")
    public Entity info(@PathVariable("id") String id)throws SQLException{
        if (validation.isValidLong(id))return idao.getByID(Long.parseLong(id));
        else return null;
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id)throws SQLException{
       if (validation.isValidLong(id))return idao.delete(Long.parseLong(id));
       else return false;
    }

    @Override
    @GetMapping(value = "/info")
    public List<Entity> infoByColumn(@RequestParam("property") String column,
                                     @RequestParam("data") String data)throws SQLException{
        return idao.getByColumn(column, Long.parseLong(data));
    }

    @Override
    @PutMapping
    public boolean update(Entity entity)throws SQLException{
        if(validation.isValidEntity(entity)) return idao.update(entity);
        else return false;

    }

    public abstract IDAO getDAO()throws SQLException;
}
