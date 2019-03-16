package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.entities.Entity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public interface IController<E extends Entity> {

    @PostMapping("/insert")
    boolean insert(@RequestBody E entity)throws SQLException;

//    @PutMapping("/update/{e}")
//    boolean update(@PathVariable("e") Entity entity) throws SQLException;
//
    @GetMapping("/info/{id}")
    Entity info(@PathVariable String id) throws SQLException;
//
//    @GetMapping(value = "/info", params = {})
//    List<Object> InfoBycolumn() throws SQLException;
//
//    @DeleteMapping("/delete/{id}")
//    boolean delete(@PathVariable("id") String id)throws SQLException;
}
