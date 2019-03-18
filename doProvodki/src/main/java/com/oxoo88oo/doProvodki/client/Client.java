package com.oxoo88oo.doProvodki.client;

import com.oxoo88oo.doProvodki.entities.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class Client<E extends Entity> implements IClient<E> {

  final Class<E> type;
  public Client(Class<E> type) { this.type = type; }




  RestTemplate restTemplate;
  public final String BaseURL = "http://localhost:8080/";
  static Logger logger = LoggerFactory.getLogger(Client.class);

  @Override
  public List<E> getAll(){

    restTemplate = new RestTemplate();
    ResponseEntity<List<E>> response = restTemplate.exchange(
            BaseURL + getEntityName() + "/info",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>(){});

    return response.getBody();


  }

  @Override
  public boolean send(E toBeSent){
    ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();

    restTemplate = new RestTemplate(requestFactory);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<E> entity = new HttpEntity<E>(toBeSent);
    restTemplate.postForObject(BaseURL + getEntityName() + "insert", entity, type);// todo

    return true;
  }


  public abstract String getEntityName();


  public ClientHttpRequestFactory getClientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectTimeout(30);
    return clientHttpRequestFactory;
  }

}
