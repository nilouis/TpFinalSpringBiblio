package fr.training.spring.library.service;

import fr.training.spring.library.entity.Bibliotheque;
import fr.training.spring.library.infrastructure.BibliothequeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliothequeServiceImpl implements  BibliothequeService{

    @Autowired
    BibliothequeDAO bibliothequeDAO;

    @Override
    public void creerNouvelleBibliothque(Bibliotheque newBibliotheque) {
        System.out.println("Service : créer nouvelle bibliothèque");
        bibliothequeDAO.save(newBibliotheque);
    }
}
