package com.befrank.casedeveloperjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BigDecimal> calculate(
            @RequestParam Integer deelnemerId,
            @RequestParam Integer pensioenleeftijd
    ) {
        BigDecimal pensioenwaarde = pensioenService.berekenWaardePensioen(deelnemerId, pensioenleeftijd, LocalDate.now());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "http://localhost:4200");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(pensioenwaarde);
    }

}