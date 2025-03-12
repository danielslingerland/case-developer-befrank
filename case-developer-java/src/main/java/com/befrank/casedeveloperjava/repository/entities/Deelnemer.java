package com.befrank.casedeveloperjava.repository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Getter
@Setter
public class Deelnemer {
    @Id
    private Integer id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private String postcode;
    private String straat;
    private String plaats;
    private String land;
    private Integer huisnummer;
    private String huisnummertoevoeging;
    private String email;
    private LocalDate geboortedatum;
    private String pensioenrekening;
    @OneToMany
    private List<Dienstverband> dienstverbanden;


    public Integer leeftijd(LocalDate peildatum){
        return Period.between(geboortedatum, peildatum).getYears();
    }

    public BigDecimal jaarlijksePremieStorting(LocalDate peildatum){
        return dienstverbanden.stream()
                .map(dienstverband -> dienstverband.jaarlijksePremieStorting(peildatum))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
