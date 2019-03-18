package com.oxoo88oo.doProvodki.client;

import com.oxoo88oo.doProvodki.entities.Entity;
import com.oxoo88oo.doProvodki.entities.Payment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PaymentClient extends Client<Payment> {
    private final String name = "Payment";

    public PaymentClient(Class<Payment> type) {
        super(type);
    }

    @Override
    public String getEntityName() {
        return this.name;
    }


}
