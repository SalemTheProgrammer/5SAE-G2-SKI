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
        logger.info("Starting retrieval of all pistes");
        List<Piste> pistes = pisteRepository.findAll();
        logger.debug("Number of pistes retrieved: {}", pistes.size());
        return pisteRepository.findAll();
    }

    @Override
    public Piste addPiste(Piste piste) {
        logger.info("Attempting to add new piste with name: {}", piste.getNamePiste());
        try {
           // Piste savedPiste = pisteRepository.save(piste);
          //  logger.info("Piste added successfully with ID: {}", savedPiste.getNumPiste());
        return pisteRepository.save(piste);
        } catch (Exception e) {
            logger.error("Error adding piste: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void removePiste(Long numPiste) {
        logger.info("Attempting to remove piste with ID: {}", numPiste);
        if (pisteRepository.existsById(numPiste)) {
            pisteRepository.deleteById(numPiste);
            logger.info("Piste with ID: {} removed successfully", numPiste);
        } else {
            logger.warn("Piste with ID: {} does not exist", numPiste);
        }
        pisteRepository.deleteById(numPiste);
    }


    @Override
    public Piste retrievePiste(Long numPiste) {
        logger.info("Retrieving piste with ID: {}", numPiste);
        //return pisteRepository.findById(numPiste).orElse(null);

        return pisteRepository.findById(numPiste).orElseGet(() -> {
            logger.warn("Piste with ID: {} not found", numPiste);
            return null;
        });
    }
}



