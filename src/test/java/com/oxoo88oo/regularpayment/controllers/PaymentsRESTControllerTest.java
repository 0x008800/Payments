package com.oxoo88oo.regularpayment.controllers;

import com.oxoo88oo.regularpayment.entities.Payment;
import com.oxoo88oo.regularpayment.exceptions.ImpossibleException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentsRESTController.class)
public class PaymentsRESTControllerTest {

   // private PaymentsRESTController controller;
    //private Payment payment;
/*
    @Before
    public void init(){
        payment = new Payment();
        controller = new PaymentsRESTController();
        payment.setId(1);
        payment.setNameOfSender("0");
        payment.setCount(new BigDecimal("100"));
        payment.setINNofSender(123L);
        payment.setAccauntOfReceiver(456);
        payment.setMFOofReceiver(789);
        payment.setOKPOofReceiver(0550);
        payment.setNameOfReceiver("1");
        payment.setNumberOfCardOfSender(231424421);
        payment.setPeriod(60000);
    }*/

@Autowired
MockMvc mock;

@MockBean Payment payment;

    @Test
    public void createPayment() throws Exception, ImpossibleException {
        final String createPaymentQuery =
                "{\"nameOfSender\": \"name\"," +
                " \"innofSender\": 12345," +
                " \"numberOfCardOfSender\": 132," +
                " \"accauntOfReceiver\": 888," +
                " \"mfoofReceiver\": 987," +
                " \"okpoofReceiver\": 765," +
                " \"nameOfReceiver\": \"receiver1\"," +
                " \"period\": 15415," +
                " \"count\": 100}";

        MvcResult result = mock.perform(MockMvcRequestBuilders.post("/payment/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(createPaymentQuery))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals(content, "true");

    }

    @Test
    public void updatePayment() {
    }

    @Test
    public void deletePayment() {
    }

    @Test
    public void getPaymentInfo() {
    }

    @Test
    public void paymentsInfoByINN() {
    }

    @Test
    public void paymentsInfoByOKPO() {
    }

    @Test
    public void createProvodka() {
    }

    @Test
    public void updateProvodka() {
    }

    @Test
    public void deleteProvodka() {
    }

    @Test
    public void provodkaByID() {
    }

    @Test
    public void getProvodkiByPaymentId() {
    }

    @Test
    public void usePayment() {
    }

    @Test
    public void isNeeded() {
    }

    @Test
    public void stornirovkaProvodki() {
    }

    @Test
    public void getInfoReceiverByaccaunt() {
    }

    @Test
    public void getInfoByProvodkiOfPayment() {
    }
}