package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.DAO.DAOProvodki;
import com.oxoo88oo.regularpayment.DAO.IDAO;
import com.oxoo88oo.regularpayment.entities.Provodka;
import com.oxoo88oo.regularpayment.validation.IValidator;
import com.oxoo88oo.regularpayment.validation.ProvodkaValidator;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/provodka")
public class ProvodkiRESTController extends ParentRESTController<Provodka>{


    protected ProvodkiRESTController() throws SQLException {
    }

    @Override
    public IDAO getDAO() throws SQLException {
        return new DAOProvodki();
    }

    @Override
    public IValidator getValidator() {
        return new ProvodkaValidator();
    }
}
