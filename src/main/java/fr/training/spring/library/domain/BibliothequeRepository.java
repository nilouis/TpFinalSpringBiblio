package fr.training.spring.library.domain;

import java.util.List;
import java.util.Optional;

public interface BibliothequeRepository {

    void save(Bibliotheque newBibliotheque);

    Bibliotheque findById(long idATrouver);

    List<Bibliotheque> findAll();

    List<Bibliotheque> findByType(TypeDeBibliotheque typeAChercher);

    List<Bibliotheque> findByDirecteurNom(String nomAChercher);

    void deleteById(long idASupprimer);
}
