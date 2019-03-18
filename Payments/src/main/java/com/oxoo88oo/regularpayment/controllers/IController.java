package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.entities.Entity;
import com.oxoo88oo.regularpayment.validation.IValidator;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public interface IController<E extends Entity> {

    @PostMapping("/insert")
    boolean insert(@RequestBody E entity)throws SQLException;

    @PutMapping("/update/{e}")
    boolean update(@PathVariable("e") Entity entity) throws SQLException;

    @GetMapping("/info/{id}")
    Entity info(@PathVariable String id) throws SQLException;

    @GetMapping(value = "/info")
    List<Entity> infoByColumn(@RequestParam("property") String column,
                         @RequestParam("data") String data) throws SQLException;

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable("id") String id)throws SQLException;

    IValidator getValidator();
}
