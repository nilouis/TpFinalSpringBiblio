package fr.training.spring.library.service;

import fr.training.spring.library.domain.*;
import fr.training.spring.library.domain.exception.BibliothequeNotFoundException;
import fr.training.spring.library.domain.exception.LivreNotFoundException;
import fr.training.spring.library.domain.BibliothequeRepository;
import fr.training.spring.library.infrastructure.LivreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BibliothequeServiceImpl implements  BibliothequeService{

    @Autowired
    BibliothequeRepository bibliothequeRepository;
    @Autowired
    LivreRepository livreRepository;

    @Override
    @Transactional(readOnly = false)
    public void creerNouvelleBibliothque(Bibliotheque newBibliotheque) {
        System.out.println("Service : créer nouvelle bibliothèque");
        bibliothequeRepository.save(newBibliotheque);
    }

    @Override
    @Transactional(readOnly=true)
    public Bibliotheque chercherBibliotheque(long idATrouver) {
        return bibliothequeRepository.findById(idATrouver);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Bibliotheque> chercherTouteLesBibliotheques() {
        return (List<Bibliotheque>) bibliothequeRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Bibliotheque> chercherBibliothequeParType(TypeDeBibliotheque typeAChercher) {
        return (List<Bibliotheque>) bibliothequeRepository.findByType(typeAChercher);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Bibliotheque> chercherBibliothequeParNomDeDirecteur(String nomAChercher) {
        return (List<Bibliotheque>) bibliothequeRepository.findByDirecteurNom(nomAChercher);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBiliotheque(Bibliotheque bibliothqueAMAJ) {
        Bibliotheque bibliothequeExistante=this.chercherBibliotheque(bibliothqueAMAJ.getId());
        bibliothequeExistante.update(bibliothqueAMAJ);
        bibliothequeRepository.save(bibliothequeExistante);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBibliotheque(long idASupprimer) {
        bibliothequeRepository.deleteById(idASupprimer);
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

        return livreRepository.findById(idDeLivre);

    }


}
