package com.befrank.casedeveloperjava;

import com.befrank.casedeveloperjava.beleggingservice.BeleggingsserviceClient;
import com.befrank.casedeveloperjava.beleggingservice.Fonds;
import com.befrank.casedeveloperjava.repository.DeelnemerRepository;
import com.befrank.casedeveloperjava.repository.entities.Deelnemer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class PensioenService {

    private static final BigDecimal RENDEMENT_JAAR = new BigDecimal("0.03");

    @Autowired
    private DeelnemerRepository deelnemerRepository;

    @Autowired
    private BeleggingsserviceClient beleggingsserviceClient;

    public BigDecimal berekenWaardePensioen(Integer deelnemerId, Integer pensionleeftijd, LocalDate peildatumBegin){
        Deelnemer deelnemer = deelnemerRepository.findById(deelnemerId).orElseThrow(() -> new IllegalArgumentException("Deelnemer not found"));
        List<Fonds> fondsen = beleggingsserviceClient.getFondsen(deelnemer.getPensioenrekening());
        BigDecimal waardeFondsen = fondsen.stream().map(Fonds::waarde).reduce(BigDecimal.ZERO, BigDecimal::add);

        for(LocalDate peildatum = peildatumBegin; deelnemer.leeftijd(peildatum) < pensionleeftijd; peildatum = peildatum.plusYears(1)){
            BigDecimal jaarlijksePremieStorting = deelnemer.jaarlijksePremieStorting(peildatum);
            waardeFondsen = waardeVolgendJaar(waardeFondsen, jaarlijksePremieStorting);
        }
        return waardeFondsen.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal waardeVolgendJaar(BigDecimal huidigeWaarde, BigDecimal jaarlijksePremieStorting){
        return jaarlijksePremieStorting
                .divide(BigDecimal.TWO, RoundingMode.HALF_UP)
                .add(huidigeWaarde)
                .multiply(RENDEMENT_JAAR)
                .add(huidigeWaarde)
                .add(jaarlijksePremieStorting);
    }
}
