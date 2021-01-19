package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.TypeDeBibliotheque;
import fr.training.spring.library.infrastructure.entity.BibliothequeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BibliothequeDAO extends CrudRepository<BibliothequeEntity, Long> {


    @Query("SELECT b FROM BibliothequeEntity b WHERE b.type = ?1")
    BibliothequeEntity findByType(TypeDeBibliotheque typeAChercher);

    @Query("SELECT bib FROM BibliothequeEntity bib WHERE bib.directeurNom = ?1")
    List<BibliothequeEntity> findByDirecteurNom(String nomAChercher);

}
