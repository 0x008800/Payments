package com.oxoo88oo.regularpayment.controllers;


import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageTablesRESTController {

    //create all
    @GetMapping("/createall")
    public boolean createAllTables() throws ImpossibleException {
        return true;
    }

    //drop all
    @GetMapping("/dropall")
    public boolean dropAllTables() throws ImpossibleException {
        return true;
    }


}
