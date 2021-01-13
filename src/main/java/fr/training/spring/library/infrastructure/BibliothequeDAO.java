package fr.training.spring.library.infrastructure;

import fr.training.spring.library.entity.Bibliotheque;
import fr.training.spring.library.entity.TypeDeBibliotheque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;


public interface BibliothequeDAO extends CrudRepository<Bibliotheque, Long> {


    @Query("SELECT b FROM Bibliotheque b WHERE b.type = ?1")
    List<Bibliotheque> findByType(TypeDeBibliotheque typeAChercher);

    @Query("SELECT bib FROM Bibliotheque bib WHERE bib.directeur.nom = ?1")
    List<Bibliotheque> findByDirecteurNom(String nomAChercher);
}
