package com.oxoo88oo.doProvodki.client;

import com.oxoo88oo.doProvodki.entities.Payment;
import com.oxoo88oo.doProvodki.entities.Provodka;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ProvodkaClient extends Client<Provodka> {
    private final String name = "Provodka";

    public ProvodkaClient(Class<Provodka> type) {
        super(type);
    }


    @Override
    public String getEntityName() {
        return this.name;
    }

}
