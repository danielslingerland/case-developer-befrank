package com.befrank.casedeveloperjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class PensioenController {

    @Autowired
    private PensioenService pensioenService;

    @GetMapping("/pensioenwaarde")
    public BigDecimal calculate(
            @RequestParam Integer klant,
            @RequestParam Integer leeftijd
    ) {
        return pensioenService.berekenWaardePensioen(klant, leeftijd, LocalDate.now());
    }

}