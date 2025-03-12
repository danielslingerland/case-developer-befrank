package com.befrank.casedeveloperjava;

import com.befrank.casedeveloperjava.beleggingservice.BeleggingsserviceClient;
import com.befrank.casedeveloperjava.beleggingservice.Fonds;
import com.befrank.casedeveloperjava.repository.DeelnemerRepository;
import com.befrank.casedeveloperjava.repository.entities.Deelnemer;
import com.befrank.casedeveloperjava.repository.entities.Dienstverband;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PensioenServiceTest {
    private static final String PENSIOENREKENING = "1a2b3c4d";
    private static final Integer DEELNEMER_ID = 1;
    private static final BigDecimal HUIDIGE_WAARDE = new BigDecimal("100000.00");
    private static final LocalDate PEILDATUM = LocalDate.of(2025, 3, 12);

    @Mock
    private BeleggingsserviceClient beleggingsserviceClient;

    @Mock
    private DeelnemerRepository deelnemerRepository;

    @InjectMocks
    private PensioenService pensioenService;

    @Test
    void berekenWaardePensioenVolgendJaar() {
        when(deelnemerRepository.findById(DEELNEMER_ID)).thenReturn(Optional.of(createDeelnemer()));
        when(beleggingsserviceClient.getFondsen(PENSIOENREKENING)).thenReturn(List.of(new Fonds("", HUIDIGE_WAARDE)));

        BigDecimal result = pensioenService.berekenWaardePensioen(DEELNEMER_ID, 61, PEILDATUM);

        assertEquals(new BigDecimal("104802.68"), result);
    }

    @Test
    void berekenWaardePensioenOverVijfJaar(){
        when(deelnemerRepository.findById(DEELNEMER_ID)).thenReturn(Optional.of(createDeelnemer()));
        when(beleggingsserviceClient.getFondsen(PENSIOENREKENING)).thenReturn(List.of(new Fonds("", HUIDIGE_WAARDE)));

        BigDecimal result = pensioenService.berekenWaardePensioen(DEELNEMER_ID, 65, PEILDATUM);

        assertEquals(new BigDecimal("125498.08"), result);
    }

    @Test
    void fondsenOptellen(){
        when(deelnemerRepository.findById(DEELNEMER_ID)).thenReturn(Optional.of(createDeelnemer()));
        when(beleggingsserviceClient.getFondsen(PENSIOENREKENING))
                .thenReturn(List.of(
                        new Fonds("", new BigDecimal("10000.00")),
                        new Fonds("", new BigDecimal("20000.00")),
                        new Fonds("", new BigDecimal("30000.00")),
                        new Fonds("", new BigDecimal("40000.00"))
                ));

        BigDecimal result = pensioenService.berekenWaardePensioen(DEELNEMER_ID, 65, PEILDATUM);

        assertEquals(new BigDecimal("125498.08"), result);
    }

    private Deelnemer createDeelnemer(){
        Dienstverband dienstverband = new Dienstverband();
        dienstverband.setSalaris(new BigDecimal("60000.00"));
        dienstverband.setParttime(new BigDecimal("0.80"));
        dienstverband.setIngansdatum(LocalDate.of(2000, 1, 1));
        Deelnemer deelnemer = new Deelnemer();
        deelnemer.setId(DEELNEMER_ID);
        deelnemer.setPensioenrekening(PENSIOENREKENING);
        deelnemer.setGeboortedatum(LocalDate.of(1965, 1, 1));
        deelnemer.setDienstverbanden(List.of(dienstverband));
        return deelnemer;
    }


}
