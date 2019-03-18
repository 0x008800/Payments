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
@WebMvcTest(ParentRESTController.class)
public class ParentRESTControllerTest {

  @Autowired MockMvc mock;

  @MockBean Payment payment;

  @Test
  public void insert() throws Exception, ImpossibleException {
    final String createPaymentQuery =
        "{\"nameOfSender\": \"name\","
            + " \"innofSender\": 12345,"
            + " \"numberOfCardOfSender\": 132,"
            + " \"accauntOfReceiver\": 888,"
            + " \"mfoofReceiver\": 987,"
            + " \"okpoofReceiver\": 765,"
            + " \"nameOfReceiver\": \"receiver1\","
            + " \"period\": 15415,"
            + " \"count\": 100}";

    MvcResult result =
        mock.perform(
                MockMvcRequestBuilders.post("/payment/insert")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createPaymentQuery))
            .andExpect(status().isOk())
            .andReturn();

    String content = result.getResponse().getContentAsString();
    assertEquals(content, "true");
  }

}