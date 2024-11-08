package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.List;
@AllArgsConstructor
@Service
public class PisteServicesImpl implements  IPisteServices{
    private static final Logger logger = LogManager.getLogger(PisteServicesImpl.class);
    private IPisteRepository pisteRepository;

    @Override
    public List<Piste> retrieveAllPistes() {


        return pisteRepository.findAll();
    }

    @Override
    public Piste addPiste(Piste piste) {

        return pisteRepository.save(piste);

    }

    @Override
    public Piste updatePiste(Piste piste) {
        return null;
    }

    @Override
    public void removePiste(Long numPiste) {

        pisteRepository.deleteById(numPiste);
    }


    @Override
    public Piste retrievePiste(Long numPiste) {

        return pisteRepository.findById(numPiste).orElse(null);


   }
}



