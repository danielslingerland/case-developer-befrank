package com.befrank.casedeveloperjava.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Dienstverband {
    private static final BigDecimal FRANCHISE = new BigDecimal("15599");
    private static final BigDecimal BESCHIKBARE_PREMIE_FRACTIE = new BigDecimal("0.05");

    @Id
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "DEELNEMER_ID", nullable=false)
    private Deelnemer deelnemer;
    @ManyToOne
    @JoinColumn(name = "WERKGEVER_ID", nullable=false)
    private Werkgever werkgever;
    @Column(name="SALARIS", nullable=false)
    private BigDecimal salaris;
    @Column(name="PARTTIME", nullable=false)
    private BigDecimal parttime;
    @Column(name="INGANSDATUM", nullable=false)
    private LocalDate ingansdatum;
    @Column(name="EINDDATUM")
    private LocalDate einddatum;


    public BigDecimal jaarlijksePremieStorting(LocalDate peildatum){
        if(ingansdatum.isAfter(peildatum)) return BigDecimal.ZERO;
        if(einddatum != null && einddatum.isBefore(peildatum)) return BigDecimal.ZERO;
        return salaris
                .subtract(FRANCHISE)
                .multiply(parttime)
                .multiply(BESCHIKBARE_PREMIE_FRACTIE);
    }
}
