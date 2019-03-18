package com.oxoo88oo.doProvodki.client;

import com.oxoo88oo.doProvodki.entities.Payment;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest extends TestCase {
//
//  @Mock
//  private RestTemplate restTemplate;
//
//  @InjectMocks
//  private IClient paymentClient = new PaymentClient();
//  @InjectMocks
//  private IClient provodkaClientClient = new ProvodkaClient();
//
//  @Test
//  public void testGetAll() {
//    List<Payment> payment = new ArrayList<Payment>();
//
//
//
//    Mockito
//            .when(restTemplate.getForEntity("http://localhost:8080/payment/info", Payment.class))
//            .thenReturn(new ResponseEntity(payment, HttpStatus.OK));
//
//    List<Payment> p = paymentClient.getAll();
//    Assert.assertEquals(payment, p);
//  }

  public void testSend() {

  }
}