package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.BibliothequeRepository;
import fr.training.spring.library.domain.TypeDeBibliotheque;
import fr.training.spring.library.domain.exception.BibliothequeNotFoundException;
import fr.training.spring.library.infrastructure.entity.BibliothequeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BibliothequeRepositoryImpl implements BibliothequeRepository {

    @Autowired
    BibliothequeDAO bibliothequeDAO;

    @Override
    public void save(Bibliotheque newBibliotheque) {
        BibliothequeEntity newBibliothequeEntity =new BibliothequeEntity(newBibliotheque);
        bibliothequeDAO.save(newBibliothequeEntity);
    }

    @Override
    public Bibliotheque findById(long idATrouver) {
        BibliothequeEntity bibliothequeTrouvee= bibliothequeDAO.findById(idATrouver).orElseThrow(
                ()-> new BibliothequeNotFoundException("Aucune bibliothèque ne correspond à cet identifiant"));
        return bibliothequeTrouvee.convertFromEntity();
    }

    @Override
    public List<Bibliotheque> findAll() {
        List<BibliothequeEntity> bibliotequesEntity= (List<BibliothequeEntity>) bibliothequeDAO.findAll();
        return bibliotequesEntity.stream().map(bibliothequeEntity -> bibliothequeEntity.convertFromEntity()).collect(Collectors.toList());
    }

    @Override
    public List<Bibliotheque> findByType(TypeDeBibliotheque typeAChercher) {
        List<BibliothequeEntity> bibliotequesEntity= (List<BibliothequeEntity>) bibliothequeDAO.findByType(typeAChercher);

        return bibliotequesEntity.stream().map(bibliothequeEntity -> bibliothequeEntity.convertFromEntity()).collect(Collectors.toList());
    }

    @Override
    public List<Bibliotheque> findByDirecteurNom(String nomAChercher) {
        List<BibliothequeEntity> bibliotequesEntity= (List<BibliothequeEntity>) bibliothequeDAO.findByDirecteurNom(nomAChercher);
        return bibliotequesEntity.stream().map(bibliothequeEntity -> bibliothequeEntity.convertFromEntity()).collect(Collectors.toList());
    }

    @Override
    public void deleteById(long idASupprimer) {

    }
}
