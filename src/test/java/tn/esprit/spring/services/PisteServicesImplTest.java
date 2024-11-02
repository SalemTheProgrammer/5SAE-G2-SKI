package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PisteServicesImplTest {

    @Mock
    IPisteRepository pisteRepository;

    @InjectMocks
    PisteServicesImpl pisteServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void retrieveAllPistes() {
        List<Piste> pistes = new ArrayList<>();
        pistes.add(new Piste());
        pistes.add(new Piste());

        when(pisteRepository.findAll()).thenReturn(pistes);

        List<Piste> result = pisteServices.retrieveAllPistes();
        assertEquals(2, result.size());
        verify(pisteRepository, times(1)).findAll();
    }

    @Test
    void addPiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Test Piste");

        when(pisteRepository.save(piste)).thenReturn(piste);

        Piste result = pisteServices.addPiste(piste);
        assertNotNull(result);
        assertEquals("Test Piste", result.getNamePiste());
        verify(pisteRepository, times(1)).save(piste);
    }

    @Test
    void removePiste() {
        Long numPiste = 1L;

        doNothing().when(pisteRepository).deleteById(numPiste);

        pisteServices.removePiste(numPiste);
        verify(pisteRepository, times(1)).deleteById(numPiste);
    }

    @Test
    void retrievePiste() {
        Long numPiste = 1L;
        Piste piste = new Piste();
        piste.setNumPiste(numPiste);

        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        Piste result = pisteServices.retrievePiste(numPiste);
        assertNotNull(result);
        assertEquals(numPiste, result.getNumPiste());
        verify(pisteRepository, times(1)).findById(numPiste);
    }
}
