package com.befrank.casedeveloperjava.beleggingservice;


import java.math.BigDecimal;

public record Fonds(
        String naam,
        BigDecimal waarde
) {
}
