package com.befrank.casedeveloperjava.beleggingservice.mock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeleggingsserviceMock {

    @GetMapping(
            value = "rekening/{rekeningnummer}/fondsen",
            produces="application/json"
    )
    public String rekening(@PathVariable String rekeningnummer) {
        return """
                [
                    {
                       "naam": "Fonds1",
                       "waarde": 10000.00
                     },
                     {
                       "naam": "Fonds2",
                       "waarde": 90000.00
                     }
                ]
                """;
    }

}
