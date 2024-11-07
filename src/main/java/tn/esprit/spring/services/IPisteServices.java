package tn.esprit.spring.services;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Piste;

import java.util.List;

public interface IPisteServices {

    List<Piste> retrieveAllPistes();

    Piste  addPiste(Piste  piste);
    Piste updatePiste(Piste piste);
    void removePiste (Long numPiste);

    Piste retrievePiste (Long numPiste);
}
