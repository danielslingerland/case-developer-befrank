package com.befrank.casedeveloperjava.beleggingservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class BeleggingsserviceClient {

    private static final String URL = "http://localhost:8080/rekening/{nummer}/fondsen";

    private final RestClient restClient = RestClient.create();

    public List<Fonds> getFondsen(String rekeningnummer) {
        return restClient.get()
                .uri(URL.replace("{nummer}", rekeningnummer))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

    }

}
