package fr.training.spring.library.service;

import fr.training.spring.library.entity.Bibliotheque;
import fr.training.spring.library.entity.TypeDeBibliotheque;
import fr.training.spring.library.infrastructure.BibliothequeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BibliothequeServiceImpl implements  BibliothequeService{

    @Autowired
    BibliothequeDAO bibliothequeDAO;

    @Override
    @Transactional(readOnly = false)
    public void creerNouvelleBibliothque(Bibliotheque newBibliotheque) {
        System.out.println("Service : créer nouvelle bibliothèque");
        bibliothequeDAO.save(newBibliotheque);
    }

    @Override
    @Transactional(readOnly=true)
    public Bibliotheque chercherBibliotheque(long idATrouver) {
        return bibliothequeDAO.findById(idATrouver).orElseThrow(() -> new EntityNotFoundException("Il n'y a pas de bibliothèque pour le critère fourni, id = " + idATrouver));
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
    public void updateBiliotheque(Bibliotheque bibliothqueAMAJ) {
        Bibliotheque bibliothequeExistante=this.chercherBibliotheque(bibliothqueAMAJ.getId());
        bibliothequeExistante.update(bibliothqueAMAJ);
        bibliothequeDAO.save(bibliothequeExistante);
    }

    @Override
    public void deleteBibliotheque(long idASupprimer) {
        bibliothequeDAO.deleteById(idASupprimer);
    }


}
