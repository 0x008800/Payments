package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.DAO.DAOPayment;
import com.oxoo88oo.regularpayment.DAO.IDAO;
import com.oxoo88oo.regularpayment.entities.Payment;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/payment")
public class PaymentsRESTController extends ParentRESTController<Payment>{

    @Override
    public IDAO getDAO() throws SQLException {
        return new DAOPayment();
    }

    protected PaymentsRESTController() throws SQLException {
    }
}
