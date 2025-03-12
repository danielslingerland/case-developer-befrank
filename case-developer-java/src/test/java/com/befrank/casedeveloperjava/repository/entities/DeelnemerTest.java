package com.befrank.casedeveloperjava.repository.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DeelnemerTest {

    @Test
    void testLeeftijd(){
        var deelnemer = new Deelnemer();
        deelnemer.setGeboortedatum(LocalDate.of(1996, 10, 5));

        var peildatum = LocalDate.of(2025, 3, 11);

        var leeftijd = deelnemer.leeftijd(peildatum);
        assertEquals(28, leeftijd.intValue());
    }


}
