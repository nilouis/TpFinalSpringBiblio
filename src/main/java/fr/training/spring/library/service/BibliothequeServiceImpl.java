package fr.training.spring.library.service;

import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.TypeDeBibliotheque;
import fr.training.spring.library.domain.exception.BibliothequeNotFoundException;
import fr.training.spring.library.domain.exception.LivreNotFoundException;
import fr.training.spring.library.infrastructure.BibliothequeDAO;
import fr.training.spring.library.infrastructure.LivreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BibliothequeServiceImpl implements  BibliothequeService{

    @Autowired
    BibliothequeDAO bibliothequeDAO;
    @Autowired
    LivreDAO livreDAO;

    @Override
    @Transactional(readOnly = false)
    public void creerNouvelleBibliothque(Bibliotheque newBibliotheque) {
        System.out.println("Service : créer nouvelle bibliothèque");
        bibliothequeDAO.save(newBibliotheque);
    }

    @Override
    @Transactional(readOnly=true)
    public Bibliotheque chercherBibliotheque(long idATrouver) {
        return bibliothequeDAO.findById(idATrouver).orElseThrow(() -> new BibliothequeNotFoundException("Il n'y a pas de bibliothèque pour le critère fourni, id = " + idATrouver));
    }

    @Override
    @Transactional(readOnly=true)
    public List<Bibliotheque> chercherTouteLesBibliotheques() {
        return (List<Bibliotheque>) bibliothequeDAO.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Bibliotheque> chercherBibliothequeParType(TypeDeBibliotheque typeAChercher) {
        return (List<Bibliotheque>) bibliothequeDAO.findByType(typeAChercher);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Bibliotheque> chercherBibliothequeParNomDeDirecteur(String nomAChercher) {
        return (List<Bibliotheque>) bibliothequeDAO.findByDirecteurNom(nomAChercher);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBiliotheque(Bibliotheque bibliothqueAMAJ) {
        Bibliotheque bibliothequeExistante=this.chercherBibliotheque(bibliothqueAMAJ.getId());
        bibliothequeExistante.update(bibliothqueAMAJ);
        bibliothequeDAO.save(bibliothequeExistante);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBibliotheque(long idASupprimer) {
        bibliothequeDAO.deleteById(idASupprimer);
    }

    @Override
    @Transactional(readOnly = false)
    public void ajouterLivre(Bibliotheque bibliothequeARemplir) {
        Bibliotheque ancienneBibliotheque=chercherBibliotheque(bibliothequeARemplir.getId());
        ancienneBibliotheque.ajouterLivreAuCatalogue(bibliothequeARemplir.getCatalogue().get(0));
        updateBiliotheque(ancienneBibliotheque);
    }

    @Override
    @Transactional(readOnly = true)
    public Livre rechercherLivre(long idDeLivre) {

        return livreDAO.findById(idDeLivre).orElseThrow(()->new LivreNotFoundException("Il n y'a pas de livre avec l'identifiant : " + idDeLivre,idDeLivre));
    }


}
