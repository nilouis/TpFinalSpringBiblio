package fr.training.spring.library.service;

import fr.training.spring.library.entity.Bibliotheque;
import fr.training.spring.library.entity.TypeDeBibliotheque;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BibliothequeService {

    public void creerNouvelleBibliothque(Bibliotheque newBibliotheque);

    Bibliotheque chercherBibliotheque(long idATrouver);

    List<Bibliotheque> chercherTouteLesBibliotheques();

    List<Bibliotheque> chercherBibliothequeParType(TypeDeBibliotheque typeAChercher);

    List<Bibliotheque> chercherBibliothequeParNomDeDirecteur(String nomAChercher);

    void updateBiliotheque(Bibliotheque bibliothqueAMAJ);
}
