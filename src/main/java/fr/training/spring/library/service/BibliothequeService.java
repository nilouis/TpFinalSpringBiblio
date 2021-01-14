package fr.training.spring.library.service;

import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.TypeDeBibliotheque;

import java.util.List;


public interface BibliothequeService {

    public void creerNouvelleBibliothque(Bibliotheque newBibliotheque);

    Bibliotheque chercherBibliotheque(long idATrouver);

    List<Bibliotheque> chercherTouteLesBibliotheques();

    List<Bibliotheque> chercherBibliothequeParType(TypeDeBibliotheque typeAChercher);

    List<Bibliotheque> chercherBibliothequeParNomDeDirecteur(String nomAChercher);

    void updateBiliotheque(Bibliotheque bibliothqueAMAJ);

    void deleteBibliotheque(long idASupprimer);
}
