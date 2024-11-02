package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback
class PisteServicesImplTestintegration {
    @Autowired
    private IPisteServices pisteServices;

    @Autowired
    private IPisteRepository pisteRepository;
    @Test
    void retrieveAllPistes() {
        Piste piste1 = new Piste(null, "Blue Trail", Color.BLUE, 1200, 25, null);
        Piste piste2 = new Piste(null, "Red Trail", Color.RED, 1600, 40, null);
        pisteRepository.save(piste1);
        pisteRepository.save(piste2);

        List<Piste> pistes = pisteServices.retrieveAllPistes();
        assertTrue(pistes.size() >= 2);

    }

    @Test
    void addPiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Green Trail");
        piste.setColor(Color.GREEN);
        piste.setLength(1500);
        piste.setSlope(30);

        Piste savedPiste = pisteServices.addPiste(piste);
        assertNotNull(savedPiste);
        assertNotNull(savedPiste.getNumPiste());
        assertEquals("Green Trail", savedPiste.getNamePiste());
    }

    @Test
    void removePiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Temporary Trail");
        piste.setColor(Color.BLUE);
        piste.setLength(800);
        piste.setSlope(15);

        Piste savedPiste = pisteRepository.save(piste);
        Long pisteId = savedPiste.getNumPiste();
        assertNotNull(pisteServices.retrievePiste(pisteId));

        pisteServices.removePiste(pisteId);
        assertNull(pisteServices.retrievePiste(pisteId));
    }


    @Test
    void retrievePiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Black Diamond");
        piste.setColor(Color.BLACK);
        piste.setLength(2000);
        piste.setSlope(50);

        Piste savedPiste = pisteRepository.save(piste);
        Piste retrievedPiste = pisteServices.retrievePiste(savedPiste.getNumPiste());

        assertNotNull(retrievedPiste);
        assertEquals(savedPiste.getNumPiste(), retrievedPiste.getNumPiste());
        assertEquals("Black Diamond", retrievedPiste.getNamePiste());
    }
}
